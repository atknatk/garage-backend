package tr.com.everva.garage;

import tr.com.everva.garage.model.entity.Tenant;
import tr.com.everva.garage.model.entity.Vehicle;

import java.util.Date;

public class BaseTest {

    protected final String vehicleId = "vehicle-id";
    protected final String tenantId = "tenant-id";

    protected Vehicle vehicle;
    protected Tenant tenant;

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

    protected void initModels() {
        this.initVehicle();
        this.initTenant();
        this.vehicle.setTenantId(tenant.getId());
        this.vehicle.setTenant(tenant);


    }


}
