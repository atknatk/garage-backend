package tr.com.everva.garage.exception;

public class UserNotFoundException extends EvervaRuntimeException {

    public UserNotFoundException(String id) {
        super(String.format("%s id'li kullanıcı bulunamadı. ",id));
    }
}
