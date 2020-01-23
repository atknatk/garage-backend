package tr.com.everva.garage.exception;

public class EvervaRuntimeException extends RuntimeException {
    public EvervaRuntimeException() {
    }

    public EvervaRuntimeException(String message) {
        super(message);
    }

    public EvervaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvervaRuntimeException(Throwable cause) {
        super(cause);
    }

    public EvervaRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
