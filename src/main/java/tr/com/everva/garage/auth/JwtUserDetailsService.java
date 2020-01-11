package tr.com.everva.garage.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.service.AccountService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    // Şifre 1;
    private final String password = "$2y$12$YiuymNDnKLW7HeRJLoC73ebxm9QlM/yrNiOqX4OhUjBqazIP117P6";

    public JwtUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        ResponseDto responseDto = accountService.retrieveUser(phone);
        if (responseDto.isSuccess()) {
            UserDto userDto = (UserDto) responseDto.getData();
            userDto.setPassword(password);
            return userDto;
        }
        throw new UsernameNotFoundException("User not found with username: " + phone);
    }
}
