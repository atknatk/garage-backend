package tr.com.everva.garage.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ErrorCode;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;
import tr.com.everva.garage.service.AccountService;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Şifre 1;
    private final String password = "$2y$12$YiuymNDnKLW7HeRJLoC73ebxm9QlM/yrNiOqX4OhUjBqazIP117P6";

    public JwtUserDetailsService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        ResponseDto responseDto = retrieveUser(phone);
        if (responseDto.isSuccess()) {
            UserDto userDto = (UserDto) responseDto.getData();
            userDto.setPassword(password);
            userDto.setPhone(phone);
            return userDto;
        }
        throw new UsernameNotFoundException("User not found with username: " + phone);
    }

    private ResponseDto retrieveUser(String phone){
        Optional<User> byPhone = userRepository.findByPhone(phone);
        return byPhone.isPresent() ?
                ResponseDto.builder()
                        .success(true)
                        .data(new UserDto(byPhone.get()))
                        .build() :
                ResponseDto.builder()
                        .success(false)
                        .errorCode(ErrorCode.NOT_EXIST)
                        .build();
    }
}
