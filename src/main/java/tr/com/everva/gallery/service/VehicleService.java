package tr.com.everva.gallery.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import tr.com.everva.gallery.entity.Vehicle;
import tr.com.everva.gallery.repository.VehicleRepository;
import tr.com.everva.gallery.util.TenantContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService{

    @PersistenceContext
    public EntityManager entityManager;


    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Transactional
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public List<Vehicle> list() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public Optional<Vehicle> get(UUID uuid) {
        return vehicleRepository.findById(uuid);
    }

    @Transactional
    public void delete(UUID id) {
        vehicleRepository.deleteById(id);
    }


}
