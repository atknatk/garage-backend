package tr.com.everva.garage.service;

import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.enums.ProfitEnum;
import tr.com.everva.garage.exception.NotFoundException;
import tr.com.everva.garage.mapper.VehicleMapper;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleCreateDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleSalesDto;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.*;
import tr.com.everva.garage.repository.VehicleRepository;
import tr.com.everva.garage.util.TenantContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @PersistenceContext
    public EntityManager entityManager;


    private final VehicleRepository vehicleRepository;
    private final ConfigurationService configurationService;
    private final ShareHolderService shareHolderService;
    private final IncomeService incomeService;

    public VehicleService(VehicleRepository vehicleRepository, ConfigurationService configurationService, ShareHolderService shareHolderService, IncomeService incomeService) {
        this.vehicleRepository = vehicleRepository;
        this.configurationService = configurationService;
        this.shareHolderService = shareHolderService;
        this.incomeService = incomeService;
    }

    @Transactional
    public Vehicle create(VehicleCreateDto dto) {
        Vehicle vehicle = VehicleMapper.INSTANCE.fromCreateDtoToEntity(dto);
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
    public Optional<Vehicle> get(String uuid) {
        return vehicleRepository.findById(uuid);
    }

    @Transactional
    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }


    @Transactional
    public void sales(String id, VehicleSalesDto dto) {
        Vehicle vehicle = getAndCheckExist(id);
        vehicle.setSalesPrice(dto.getSalesPrice());
        vehicle.setSold(true);
        vehicle.setSoldAt(new Date());
        List<ShareHolderDto> shareHolderList = shareHolderService.findAll();
        vehicleRepository.save(vehicle);

        Hibernate.initialize(vehicle.getExpenses());
        double sum = vehicle.getExpenses().stream().mapToDouble(Expense::getMoney).sum();
        double gain = vehicle.getSalesPrice() - vehicle.getBuyingPrice() - sum;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // TODO current User ID
        final String currentUserId = authentication.getName();


        if (shareHolderList.isEmpty() || shareHolderList.size() == 1) {
            Income income = new Income();
            income.setTenantId(TenantContext.getCurrentTenant());
            income.setUser(new User(currentUserId));
            income.setGain(gain);
            incomeService.save(income);
            return;
        }


        if (dto.getShareHoldingRates() != null && !dto.getShareHoldingRates().isEmpty()) {
            // TODO
        } else {

            final double totalRatio = shareHolderList.stream().mapToDouble(ShareHolderDto::getShareHolding).sum();
            final double perRatioGain = gain / totalRatio;

            List<Income> incomes = new ArrayList<>();
            shareHolderList.forEach(l -> {
                Income income = new Income();
                income.setTenantId(TenantContext.getCurrentTenant());
                income.setUser(new User(currentUserId));
                income.setGain(perRatioGain * l.getShareHolding());
                incomes.add(income);
            });
            incomeService.saveAll(incomes);







//            Optional<Configuration> byKey = configurationService.findByKey(ConfigurationEnum.PROFIT);
//            ProfitEnum profitEnum = null;
//            if (byKey.isPresent()) {
//                try {
//                    profitEnum = ProfitEnum.valueOf(byKey.get().getValue());
//                } catch (Exception ex) {
//                    throw new RuntimeException("profit enum not found");
//                }
//            }
//            if (profitEnum != null) {
//                if (profitEnum == ProfitEnum.RATIO) {
//
//
//                } else if (profitEnum == ProfitEnum.CAPITAL) {
//
//                }
//            }


        }


    }

    private Vehicle getAndCheckExist(String id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.orElseThrow(() -> new NotFoundException("not.found"));
    }

}
