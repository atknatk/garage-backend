package tr.com.everva.garage.exception;

public class GalleryNonExistInUserException extends EvervaRuntimeException {

    public GalleryNonExistInUserException(String id) {
        super(String.format("%s id'li kullanıcıda mevcut galeri bulunmamaktadır.. ",id));
    }
}
