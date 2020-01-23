package tr.com.everva.garage.controller.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import tr.com.everva.garage.auth.WebSecurityConfig;
import tr.com.everva.garage.exception.GalleryNonExistInUserException;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.service.UserService;
import tr.com.everva.garage.util.ContextUtils;
import tr.com.everva.garage.util.GalleryContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class GalleryFilter implements Filter {

    private final WebSecurityConfig webSecurityConfig;
    private final UserService userService;

    private static final String GALLERY_HEADER = "X-GalleryId";
    private final ObjectMapper mapper;

    public GalleryFilter(WebSecurityConfig webSecurityConfig, UserService userService) {
        this.webSecurityConfig = webSecurityConfig;
        this.userService = userService;
        mapper = new ObjectMapper();
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String galleryHeader = request.getHeader(GALLERY_HEADER);

        // İlk galeri kayıt için ignore ediliyor.
        if (request.getMethod().equals("POST") && request.getServletPath().equalsIgnoreCase("/gallery")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!this.webSecurityConfig.ignoringPaths.contains(request.getServletPath())) {
            if (galleryHeader != null && !galleryHeader.isEmpty()) {
                if (!userService.checkOwnGallery(galleryHeader)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    ResponseDto invalidKey = ResponseDto.builder().success(false).error("Invalid key").build();
                    response.getWriter().write(mapper.writeValueAsString(invalidKey));
                    return;
                }
                //throw new GalleryNonExistInUserException(ContextUtils.getCurrentUser().getId());
                GalleryContext.setCurrentGallery(galleryHeader);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write("{\"error\": \"No util supplied\"}");
                response.getWriter().flush();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
