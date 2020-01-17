package tr.com.everva.garage.util;

public class GalleryContext {

    private static ThreadLocal<String> currentGallery = new ThreadLocal<>();

    public static String getCurrentGallery() {
        return currentGallery.get();
    }

    public static void setCurrentGallery(String gallery) {
        currentGallery.set(gallery);
    }

    public static void clear() {
        currentGallery.set(null);
    }

}
