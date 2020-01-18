package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ErrorCode;
import tr.com.everva.garage.exception.UserNotFoundException;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.dto.account.RegistrationDto;
import tr.com.everva.garage.model.dto.account.VerifyDto;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountService {


    private final UserRepository userRepository;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseDto register(RegistrationDto registrationDto) {
        if (userRepository.existsByPhone(registrationDto.getPhone())) {
            return ResponseDto.builder()
                    .success(false)
                    .error("Bu telefon numarası zaten sistemde kayıtlı")
                    .build();
        }
        User user = new User();
        user.setPhone(registrationDto.getPhone());
        user.setName(registrationDto.getName());
        user.setActive(false);
        User saved = userRepository.save(user);
        return ResponseDto.builder()
                .success(true)
                .data(saved)
                .build();
    }


    public ResponseDto retrieveUser(String phone) {

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


    public ResponseDto verify(VerifyDto dto) {
        Optional<User> byPhone = userRepository.findByPhone(dto.getPhone());
        // TODO Code verify

        if(!byPhone.isPresent()){
            throw new UserNotFoundException("phone");
        }

        User user = byPhone.get();
        user.setVerify(true);
        user.setActive(true);
        userRepository.verify(user.getId());

       return ResponseDto.builder().success(true).build();
    }
}
