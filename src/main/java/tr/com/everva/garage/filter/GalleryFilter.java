package tr.com.everva.garage.filter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import tr.com.everva.garage.auth.WebSecurityConfig;
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

    private static final String GALLERY_HEADER = "X-GalleryId";

    public GalleryFilter(WebSecurityConfig webSecurityConfig) {
        this.webSecurityConfig = webSecurityConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String galleryHeader = request.getHeader(GALLERY_HEADER);
        if (!this.webSecurityConfig.ignoringPaths.contains(request.getServletPath())) {
            if (galleryHeader != null && !galleryHeader.isEmpty()) {
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
