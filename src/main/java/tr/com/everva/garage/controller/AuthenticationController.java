package tr.com.everva.garage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tr.com.everva.garage.auth.JwtTokenUtil;
import tr.com.everva.garage.auth.JwtUserDetailsService;
import tr.com.everva.garage.model.dto.account.LoginDto;
import tr.com.everva.garage.model.dto.account.LoginResponseDto;
import tr.com.everva.garage.model.dto.account.UserDto;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto loginDto) throws Exception {
        authenticate(loginDto.getPhone(), "1");
        final UserDto userDto = (UserDto) userDetailsService
                .loadUserByUsername(loginDto.getPhone());
        final String token = jwtTokenUtil.generateToken(userDto);
        return ResponseEntity.ok(new LoginResponseDto(token, userDto));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(token);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
