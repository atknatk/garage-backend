package tr.com.everva.garage.service;

import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.everva.garage.exception.NotFoundException;
import tr.com.everva.garage.exception.VehicleNotFound;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.expense.ExpenseDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleSalesDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.*;
import tr.com.everva.garage.repository.VehicleRepository;
import tr.com.everva.garage.util.ContextUtils;
import tr.com.everva.garage.util.GalleryContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public VehicleService(VehicleRepository vehicleRepository, ConfigurationService configurationService, ShareHolderService shareHolderService, IncomeService incomeService, ExpenseService expenseService) {
        this.vehicleRepository = vehicleRepository;
        this.configurationService = configurationService;
        this.shareHolderService = shareHolderService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    @Transactional
    public Vehicle create(VehicleCreateDto dto) {
        Vehicle vehicle = new Vehicle(dto);
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle update(String id, VehicleUpdateDto vehicleUpdateDto) {
        Vehicle vehicleDb = getAndCheckExist(id);
        vehicleDb.merge(vehicleUpdateDto);
        return vehicleRepository.save(vehicleDb);
    }

    @Transactional
    public List<Vehicle> list() {
        return vehicleRepository.findAll();
    }

    @Transactional
    public boolean existsById(String id) {
        return vehicleRepository.existsById(id);
    }


    @Transactional
    public Optional<Vehicle> get(String uuid) {
        return vehicleRepository.findById(uuid);
    }

    @Transactional
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }


    private Vehicle getAndCheckExist(String id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.orElseThrow(() -> new NotFoundException("not.found"));
    }

    @Transactional
    public ResponseDto sold(final String id, VehicleSalesDto dto) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        Vehicle vehicle = vehicleOptional.orElseThrow(() -> {
            throw new VehicleNotFound(id);
        });
        vehicle.setSoldPrice(dto.getSoldPrice());
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
                income.setGallery(GalleryContext.getCurrentGalleryInstance());
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
            income.setGallery(GalleryContext.getCurrentGalleryInstance());

            // TODO income note
            // income.setIncomeNote(note);

            return income;
        }).collect(Collectors.toList());
        incomeService.saveAll(incomeList);
    }


}
