package tr.com.everva.garage.exception;

public class VehicleAllReadySoldFound extends RuntimeException {
    public VehicleAllReadySoldFound(int id) {
        super(String.format("%s id'li araç zaten satılmış bulunamadı. ", id));
    }
}
