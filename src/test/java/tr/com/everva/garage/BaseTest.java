package tr.com.everva.garage;

import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.model.entity.Vehicle;

import java.util.Date;

public class BaseTest {

    protected final String vehicleId = "vehicle-id";
    protected final String galleryId = "gallery-id";
    protected final String userId = "gallery-id";

    protected User user;
    protected Vehicle vehicle;
    protected Gallery gallery;

    protected void initUser() {
        user = new User();
        user.setId(userId);
        user.setPhone("+905325250522");
    }


    protected void initVehicle() {
        vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setBoughtAt(new Date());
        vehicle.setSalesPrice(100000d);
    }

    protected void initTenant() {
        gallery = new Gallery();
        gallery.setId(galleryId);
        gallery.setGalleryName("Test Gallery");
    }

    protected void resetModels() {
        this.user = null;
        this.gallery = null;
        this.vehicle = null;
    }

    protected void initModels() {
        this.initVehicle();
        this.initTenant();
        this.initUser();
        this.vehicle.setGalleryId(gallery.getId());
        this.vehicle.setGallery(gallery);
        this.vehicle.setCreatedBy(user);
        this.user.setGalleryId(gallery.getId());
        this.user.setGallery(gallery);
    }


}
