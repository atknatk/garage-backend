package tr.com.everva.garage.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.tenant.RegistrationDto;
import tr.com.everva.garage.service.AccountService;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> updateVehicle(@Valid @RequestBody RegistrationDto dto) {
        ResponseDto registered = accountService.register(dto);
        return ResponseEntity.ok(registered);
    }
}
