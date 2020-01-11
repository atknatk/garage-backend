package tr.com.everva.garage;

import tr.com.everva.garage.model.entity.Tenant;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.model.entity.Vehicle;

import java.util.Date;

public class BaseTest {

    protected final String vehicleId = "vehicle-id";
    protected final String tenantId = "tenant-id";
    protected final String userId = "tenant-id";

    protected User user;
    protected Vehicle vehicle;
    protected Tenant tenant;

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
        tenant = new Tenant();
        tenant.setId(tenantId);
        tenant.setGalleryName("Test Gallery");
    }

    protected void resetModels() {
        this.user = null;
        this.tenant = null;
        this.vehicle = null;
    }

    protected void initModels() {
        this.initVehicle();
        this.initTenant();
        this.initUser();
        this.vehicle.setTenantId(tenant.getId());
        this.vehicle.setTenant(tenant);
        this.vehicle.setCreatedBy(user);
        this.user.setTenantId(tenant.getId());
        this.user.setTenant(tenant);
    }


}
