package tr.com.everva.garage.util;

import tr.com.everva.garage.model.entity.Gallery;

public class GalleryContext {

    private static ThreadLocal<String> currentGallery = new ThreadLocal<>();

    public static String getCurrentGallery() {
        return currentGallery.get();
    }

    public static Gallery getCurrentGalleryInstance() {
        return new Gallery(currentGallery.get());
    }

    public static void setCurrentGallery(String gallery) {
        currentGallery.set(gallery);
    }

    public static void clear() {
        currentGallery.set(null);
    }

}
