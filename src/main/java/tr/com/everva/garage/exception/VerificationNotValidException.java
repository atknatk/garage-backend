package tr.com.everva.garage.exception;

public class VerificationNotValidException extends EvervaRuntimeException {
    public VerificationNotValidException(String code) {
        super(code);
    }
}
