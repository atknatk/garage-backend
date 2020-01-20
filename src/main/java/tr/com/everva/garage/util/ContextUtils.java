package tr.com.everva.garage.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.everva.garage.model.dto.account.BasicUserDto;
import tr.com.everva.garage.model.entity.User;

public class ContextUtils {

    public static BasicUserDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        return (BasicUserDto) principal;

    }


    public static User getCurrentUserForRepository() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        return new User((BasicUserDto) principal);
    }

}
