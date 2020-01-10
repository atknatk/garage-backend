package tr.com.everva.garage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tr.com.everva.garage.BaseTest;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.repository.VehicleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehicleServiceTest extends BaseTest {


    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private ShareHolderService shareHolderService;

    @Mock
    private IncomeService incomeService;

    @InjectMocks // auto inject helloRepository
    private VehicleService vehicleService;

    @BeforeEach
    void setMockOutput() {
        vehicleService = new VehicleService(vehicleRepository, configurationService, shareHolderService, incomeService);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
    }

    @Test
    void create() {
    }

    @Test
    void list() {
    }

    // @DisplayName("Test Mock helloService + helloRepository")
    @Test
    void get() {
        Optional<Vehicle> vehicle = vehicleService.get(vehicleId);
        assertTrue(vehicle.isPresent());
        assertEquals(vehicleId, vehicle.get().getId());
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void sales() {
    }
}
