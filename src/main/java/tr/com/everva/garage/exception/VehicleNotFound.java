package tr.com.everva.garage.exception;

public class VehicleNotFound  extends RuntimeException {
    public VehicleNotFound(String id) {
        super(String.format("%s id'li araç bulunamadı. ",id));
    }

}
