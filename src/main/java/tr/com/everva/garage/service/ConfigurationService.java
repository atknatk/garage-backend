package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.enums.ProfitEnum;
import tr.com.everva.garage.exception.NotFoundException;
import tr.com.everva.garage.mapper.VehicleMapper;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleSalesDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.Configuration;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.repository.ConfigurationRepository;
import tr.com.everva.garage.repository.ShareHolderRepository;
import tr.com.everva.garage.repository.VehicleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {

    @PersistenceContext
    public EntityManager entityManager;


    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;

    }

    public Configuration getByKey(ConfigurationEnum profit) {
        return configurationRepository.findByKey(profit).orElse(null);
    }

    public Optional<Configuration> findByKey(ConfigurationEnum profit) {
        return configurationRepository.findByKey(profit);
    }

}
