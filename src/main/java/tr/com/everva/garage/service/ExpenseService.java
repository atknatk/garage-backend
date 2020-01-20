package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.exception.VehicleNotFound;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.expense.ExpenseAddDto;
import tr.com.everva.garage.model.entity.Expense;
import tr.com.everva.garage.model.entity.Vehicle;
import tr.com.everva.garage.repository.ExpenseRepository;
import tr.com.everva.garage.util.GalleryContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ExpenseService {

    @PersistenceContext
    public EntityManager entityManager;

    private final ExpenseRepository expenseRepository;
    private final VehicleService vehicleService;

    public ExpenseService(ExpenseRepository expenseRepository, VehicleService vehicleService) {
        this.expenseRepository = expenseRepository;
        this.vehicleService = vehicleService;
    }


    public double sumAllExpenseByVehicle(String vehicleId) {
        return expenseRepository.sumAllExpensesByVehicle(vehicleId);
    }

    public ResponseDto addExpense(final String id, final ExpenseAddDto dto) {

        boolean isExist = vehicleService.existsById(id);
        if (!isExist) {
            throw new VehicleNotFound(id);
        }

        Expense expense = new Expense(dto);
        expense.setVehicle(new Vehicle(id));
        expense.setGallery(GalleryContext.getCurrentGalleryInstance());
        Expense saved = expenseRepository.save(expense);
        return ResponseDto.builder().success(true).data(saved.getId()).build();
    }
}
