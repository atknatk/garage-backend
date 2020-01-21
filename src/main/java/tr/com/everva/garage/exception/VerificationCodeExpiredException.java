package tr.com.everva.garage.exception;

public class VerificationCodeExpiredException extends RuntimeException {
    public VerificationCodeExpiredException(String code) {
        super(code);
    }
}
