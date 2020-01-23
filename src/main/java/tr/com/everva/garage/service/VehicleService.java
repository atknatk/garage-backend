package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.exception.NotFoundException;
import tr.com.everva.garage.exception.VehicleAllReadySoldException;
import tr.com.everva.garage.exception.VehicleNotFoundException;
import tr.com.everva.garage.exception.VehicleSoldException;
import tr.com.everva.garage.filter.NoGalleryFilter;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleSalesDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.Income;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.repository.VehicleRepository;
import tr.com.everva.garage.util.ContextUtils;
import tr.com.everva.garage.util.GalleryContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @PersistenceContext
    public EntityManager entityManager;


    private final VehicleRepository vehicleRepository;
    private final ConfigurationService configurationService;
    private final ShareHolderService shareHolderService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final UserService userService;

    public VehicleService(VehicleRepository vehicleRepository, ConfigurationService configurationService, ShareHolderService shareHolderService, IncomeService incomeService, ExpenseService expenseService, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.configurationService = configurationService;
        this.shareHolderService = shareHolderService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @Transactional
    @NoGalleryFilter
    public Vehicle create(VehicleCreateDto dto) {
        Vehicle vehicle = new Vehicle(dto);
        vehicle.setGalleryId(GalleryContext.getCurrentGallery());
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    @NoGalleryFilter
    public Vehicle update(int id, VehicleUpdateDto vehicleUpdateDto) {
        Vehicle vehicleDb = getAndCheckExist(id);
        if (vehicleDb.isSold()) {
            throw new VehicleSoldException();
        }
        vehicleDb.merge(vehicleUpdateDto);
        return vehicleRepository.save(vehicleDb);
    }

    @Transactional
    public List<Vehicle> list() {
        return vehicleRepository.findAll();
    }


    @Transactional
    public Optional<Vehicle> get(Integer id) {
        return vehicleRepository.findById(id);
    }


    private Vehicle getAndCheckExist(int id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.orElseThrow(() -> new NotFoundException("not.found"));
    }

    @Transactional
    public ResponseDto sold(final int id, VehicleSalesDto dto) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        Vehicle vehicle = vehicleOptional.orElseThrow(() -> {
            throw new VehicleNotFoundException(id);
        });
        if (vehicle.isSold()) {
            throw new VehicleAllReadySoldException(id);
        }

        vehicle.setSoldPrice(dto.getSoldPrice());
        vehicle.setSold(true);
        vehicle.setSoldAt(new Date());
        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        double sumExpenses = expenseService.sumAllExpenseByVehicle(vehicle.getId());
        double incomeValue = dto.getSoldPrice() - vehicle.getBuyingPrice() - sumExpenses;

        // satışa özel bir kar dağıtımı mevcut değil ise
        if (dto.getShareHoldingRates() == null || dto.getShareHoldingRates().isEmpty()) {

            List<ShareHolderDto> shareHolders = shareHolderService.findAll();

            // Tek ortaklı bir galeri ise
            if (shareHolders.isEmpty() || shareHolders.size() == 1) {
                final Income income = new Income();
                income.setUser(ContextUtils.getCurrentUserForRepository());
                income.setGain(incomeValue);
                income.setVehicle(updatedVehicle);
                income.setGalleryId(GalleryContext.getCurrentGallery());
                incomeService.save(income);
            } else {
                // Çok ortaklı bir galeri ise
                distributeDividend(updatedVehicle, shareHolders, incomeValue);
            }

        } else {
            // satışa özel bir kar dağıtımı var ise
            // TODO income note eklenecek
            List<ShareHolderDto> shareHolderDtos = dto.getShareHoldingRates()
                    .stream()
                    .map(ShareHolderDto::new).collect(Collectors.toList());
            distributeDividend(updatedVehicle, shareHolderDtos, incomeValue);

        }
        return ResponseDto.builder().success(true).build();

    }

    private void distributeDividend(final Vehicle vehicle, final List<ShareHolderDto> shareHolders, final double incomeValue) {
        this.distributeDividend(vehicle, shareHolders, incomeValue, null);
    }

    private void distributeDividend(final Vehicle vehicle, final List<ShareHolderDto> shareHolders, final double incomeValue, final String note) {
        final int denominator = shareHolders.stream().mapToInt(ShareHolderDto::getShareHolding).sum();
        List<Income> incomeList = shareHolders.stream().map(shareHolderDto -> {
            final Income income = new Income();
            income.setUser(new User(shareHolderDto.getUserId()));
            income.setVehicle(vehicle);
            income.setGain(incomeValue * shareHolderDto.getShareHolding() / denominator);
            income.setGalleryId(GalleryContext.getCurrentGallery());

            // TODO income note
            // income.setIncomeNote(note);

            return income;
        }).collect(Collectors.toList());
        incomeService.saveAll(incomeList);
    }


}
