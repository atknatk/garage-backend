package tr.com.everva.garage.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import tr.com.everva.garage.auth.JwtTokenUtil;
import tr.com.everva.garage.auth.JwtUserDetailsService;
import tr.com.everva.garage.enums.ErrorCode;
import tr.com.everva.garage.exception.UserNotFoundException;
import tr.com.everva.garage.exception.UserNotRegisteredException;
import tr.com.everva.garage.exception.VerificationCodeExpiredException;
import tr.com.everva.garage.exception.VerificationNotValidException;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.LoginResponseDto;
import tr.com.everva.garage.model.dto.account.RegistrationDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.dto.account.VerifyDto;
import tr.com.everva.garage.model.entity.SmsVerification;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.SmsVerificationRepository;
import tr.com.everva.garage.repository.UserRepository;
import tr.com.everva.garage.util.SimpleOTPGenerator;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {


    private final UserRepository userRepository;
    private final SmsVerificationRepository smsVerificationRepository;
    private final AmazonSNS snsClient;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public AccountService(UserRepository userRepository, SmsVerificationRepository smsVerificationRepository, AmazonSNS snsClient, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.smsVerificationRepository = smsVerificationRepository;
        this.snsClient = snsClient;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public ResponseDto register(RegistrationDto registrationDto) {
//        if (userRepository.existsByPhone(registrationDto.getPhone())) {
//            return ResponseDto.builder()
//                    .success(false)
//                    .error("Bu telefon numarası zaten sistemde kayıtlı")
//                    .build();
//        }

        User user = userRepository.findByPhone(registrationDto.getPhone()).orElseGet(User::new);
        user.setPhone(registrationDto.getPhone());
        user.setName(registrationDto.getName());
        user.setActive(false);
        User saved = userRepository.save(user);
        this.sendMessage(registrationDto.getPhone());
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


    @Transactional
    public ResponseDto verify(VerifyDto dto) {
        Optional<User> byUser = userRepository.findByPhone(dto.getPhone());

        if (!byUser.isPresent()) {
            throw new UserNotFoundException("phone");
        }

        Optional<SmsVerification> optionalSmsVerification = smsVerificationRepository.findByPhone(dto.getPhone());

        SmsVerification smsVerification = optionalSmsVerification.orElseThrow(() -> {
            throw new UserNotRegisteredException(dto.getPhone());
        });

        long diffInSeconds = getDateDiff(smsVerification.getCreatedAt(), new Date(), TimeUnit.SECONDS);
        if (180 <= diffInSeconds) {
            throw new VerificationCodeExpiredException(dto.getCode());
        }

        if (!smsVerification.getCode().equals(dto.getCode())) {
            throw new VerificationNotValidException(dto.getCode());
        }

        smsVerificationRepository.delete(smsVerification);

        User user = byUser.get();
        user.setVerify(true);
        user.setActive(true);
        userRepository.verify(user.getId());
        LoginResponseDto authenticationToken = createAuthenticationToken(dto.getPhone());
        return ResponseDto.builder().data(authenticationToken).success(true).build();
    }


    private void sendMessage(String phone) {
        String verificationCode = SimpleOTPGenerator.random(6);
        Optional<SmsVerification> byPhone = smsVerificationRepository.findByPhone(phone);
        SmsVerification smsVerification = byPhone.orElseGet(SmsVerification::new);
        smsVerification.setCode(verificationCode);
        smsVerification.setPhone(phone);
        smsVerification.setCreatedAt(new Date());
        smsVerificationRepository.save(smsVerification);

        PublishRequest message = new PublishRequest()
                .withMessage(verificationCode + ", Garaj App doğrulama kodunuzdur.")
                .withPhoneNumber("+" + phone);
        snsClient.publish(message);
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMilliseconds = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
    }

    public LoginResponseDto createAuthenticationToken(String phone) {
        authenticate(phone);
        final UserDto userDto = (UserDto) userDetailsService
                .loadUserByUsername(phone);
        final String token = jwtTokenUtil.generateToken(userDto);
        return new LoginResponseDto(token, userDto);
    }

    private void authenticate(String username) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, "1");
            authenticationManager.authenticate(token);
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
