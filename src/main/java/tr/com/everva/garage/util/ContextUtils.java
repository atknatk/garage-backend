package tr.com.everva.garage.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.everva.garage.model.dto.account.BasicUserDto;

public class ContextUtils {

    public static BasicUserDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        return (BasicUserDto) principal;

    }

}
