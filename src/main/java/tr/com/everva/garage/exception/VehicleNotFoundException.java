package tr.com.everva.garage.exception;

public class VehicleNotFoundException extends EvervaRuntimeException {
    public VehicleNotFoundException(int id) {
        super(String.format("%s id'li araç bulunamadı. ",id));
    }

}
