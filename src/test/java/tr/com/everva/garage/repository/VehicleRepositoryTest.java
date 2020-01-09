package tr.com.everva.garage.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.com.everva.garage.BaseTest;
import tr.com.everva.garage.model.entity.Tenant;
import tr.com.everva.garage.model.entity.Vehicle;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class VehicleRepositoryTest extends BaseTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void createVehicle() {
        initVehicle();
    }


    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(vehicleRepository).isNotNull();
    }


    @Test
    void whenSavedThenFindsByName() {
        Vehicle saved = vehicleRepository.save(vehicle);
        assertThat(saved.getId()).isEqualTo(saved.getId());

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(saved.getId());
        assertThat(optionalVehicle.isPresent()).isTrue();
        assertThat(optionalVehicle.get().getId()).isEqualTo(saved.getId());
    }


}
