package tr.com.everva.garage.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tr.com.everva.garage.BaseTest;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.RegistrationDto;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;

import static org.mockito.Mockito.when;

class AccountServiceTest extends BaseTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    void setMockOutput() {
        accountService = new AccountService(userRepository);
        initUser();
        initTenant();
        //when(galleryService.saveEmptyTenant()).thenReturn(gallery);
        User user1 = user.toBuilder().build();
        user1.setGallery(gallery);
        user1.setGalleryId(galleryId);
        when(userRepository.save(user)).thenReturn(user1);
    }

    @Test
    void register() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setPhone(this.user.getPhone());
        ResponseDto register = this.accountService.register(registrationDto);
        Assertions.assertTrue(register.isSuccess());
    }
}
