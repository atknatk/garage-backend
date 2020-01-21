package tr.com.everva.garage.exception;

public class UserNotRegisteredException extends RuntimeException {

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
