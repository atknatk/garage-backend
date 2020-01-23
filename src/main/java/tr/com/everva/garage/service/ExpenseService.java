package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.enums.ExpenseEnum;
import tr.com.everva.garage.exception.NotFoundException;
import tr.com.everva.garage.exception.VehicleNotFoundException;
import tr.com.everva.garage.exception.VehicleSoldException;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.expense.ExpenseAddOrUpdateDto;
import tr.com.everva.garage.model.dto.expense.ExpenseDto;
import tr.com.everva.garage.model.entity.Expense;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.repository.ExpenseRepository;
import tr.com.everva.garage.repository.VehicleRepository;
import tr.com.everva.garage.util.GalleryContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class ExpenseService {

    @PersistenceContext
    public EntityManager entityManager;

    private final ExpenseRepository expenseRepository;
    private final VehicleRepository vehicleRepository;

    public ExpenseService(ExpenseRepository expenseRepository, VehicleRepository vehicleRepository) {
        this.expenseRepository = expenseRepository;
        this.vehicleRepository = vehicleRepository;
    }


    public double sumAllExpenseByVehicle(int vehicleId) {
        return expenseRepository.sumAllExpensesByVehicle(vehicleId);
    }

    public ResponseDto addExpense(final int id, final ExpenseAddOrUpdateDto dto) {

        boolean isExist = vehicleRepository.existsById(id);
        if (!isExist) {
            throw new VehicleNotFoundException(id);
        }

        Expense expense = new Expense(dto);
        expense.setVehicle(new Vehicle(id));
        expense.setGalleryId(GalleryContext.getCurrentGallery());
        Expense saved = expenseRepository.save(expense);
        return ResponseDto.builder().success(true).data(saved.getId()).build();
    }

    // get ise öyle ya da böyle getir :)
    public ExpenseDto getById(final String id) {
        Optional<Expense> byId = expenseRepository.findById(id);
        Expense expense = byId.orElseThrow(() -> {
            throw new NotFoundException("İstenilenen harcama bulunamadı");
        });
        return new ExpenseDto(expense);
    }

    public ExpenseDto update(String id, ExpenseAddOrUpdateDto dto) {
        Optional<Expense> byId = expenseRepository.findById(id);
        Expense expense = byId.orElseThrow(() -> {
            throw new NotFoundException("Güncellenmek İstenilenen harcama bulunamadı");
        });

        Vehicle vehicle = expense.getVehicle();
        if (vehicle.isSold()) {
            throw new VehicleSoldException();
        }
        expense.setMoney(dto.getMoney());
        expense.setExpenseType(ExpenseEnum.valueOf(dto.getExpenseType()));
        Expense saved = expenseRepository.save(expense);
        return new ExpenseDto(saved);

    }


//    public ExpenseDto update(String id, ExpenseAddOrUpdateDto dto) {
//
//    }
}
