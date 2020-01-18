package tr.com.everva.garage.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super(String.format("%s id'li kullanıcı bulunamadı. ",id));
    }
}
