package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.repository.VehicleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

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
    public Optional<Vehicle> get(String uuid) {
        return vehicleRepository.findById(uuid);
    }

    @Transactional
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }


}
