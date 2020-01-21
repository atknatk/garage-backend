package tr.com.everva.garage.exception;

public class VerificationNotValidException extends RuntimeException {
    public VerificationNotValidException(String code) {
        super(code);
    }
}
