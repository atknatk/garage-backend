package tr.com.everva.garage.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tr.com.everva.garage.BaseTest;
import tr.com.everva.garage.model.dto.tenant.RegistrationDto;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;

import static org.mockito.Mockito.when;

class AccountServiceTest extends BaseTest {

    @Mock
    private TenantService tenantService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    void setMockOutput() {
        accountService = new AccountService(tenantService, userRepository);
        initUser();
        initTenant();
        when(tenantService.saveEmptyTenant()).thenReturn(tenant);
        User user1 = user.toBuilder().build();
        user1.setTenant(tenant);
        user1.setTenantId(tenantId);
        when(userRepository.save(user)).thenReturn(user1);
    }

    @Test
    void register() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setPhone(this.user.getPhone());
        User registeredUser = this.accountService.register(registrationDto);
        Assertions.assertEquals(registeredUser.getPhone(),this.user.getPhone());
    }
}