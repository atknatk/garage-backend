package tr.com.everva.garage.exception;

public class VehicleAllReadySoldException extends EvervaRuntimeException {
    public VehicleAllReadySoldException(int id) {
        super(String.format("%s id'li araç zaten satılmış bulunamadı. ", id));
    }
}
