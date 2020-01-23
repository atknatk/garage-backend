package tr.com.everva.garage.exception;

public class VerificationCodeExpiredException extends EvervaRuntimeException {
    public VerificationCodeExpiredException(String code) {
        super(code);
    }
}
