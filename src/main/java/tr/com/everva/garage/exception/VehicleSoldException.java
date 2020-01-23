package tr.com.everva.garage.exception;

public class VehicleSoldException extends EvervaRuntimeException {
    public VehicleSoldException() {
        super("Satılmış araç üzerinde işlem yapamazsınız");
    }
}
