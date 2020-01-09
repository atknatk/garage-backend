package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.model.entity.Configuration;
import tr.com.everva.garage.model.entity.Vehicle;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, String> {

    Optional<Configuration> findByKey(ConfigurationEnum key);


}
