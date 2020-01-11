package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ErrorCode;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.dto.tenant.RegistrationDto;
import tr.com.everva.garage.model.entity.Tenant;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountService {


    private final TenantService tenantService;
    private final UserRepository userRepository;

    public AccountService(TenantService tenantService, UserRepository userRepository) {
        this.tenantService = tenantService;
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
        Tenant savedTenant = tenantService.saveEmptyTenant();
        User user = new User();
        user.setTenantId(savedTenant.getId());
        user.setPhone(registrationDto.getPhone());
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
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


}
