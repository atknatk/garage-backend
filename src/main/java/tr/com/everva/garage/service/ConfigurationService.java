package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.model.entity.Configuration;
import tr.com.everva.garage.repository.ConfigurationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
