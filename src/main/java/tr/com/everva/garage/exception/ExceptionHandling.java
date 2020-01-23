package tr.com.everva.garage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tr.com.everva.garage.model.dto.ResponseDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice(basePackages = {"tr", "com"})
public class ExceptionHandling {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ExceptionHandler({
            EvervaRuntimeException.class,
    })
    public ResponseEntity<ResponseDto> galleryNonExistInUserException(EvervaRuntimeException ex) {
        ResponseDto build = ResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> exception(Exception ex) {
        ResponseDto build = ResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
    }

}
