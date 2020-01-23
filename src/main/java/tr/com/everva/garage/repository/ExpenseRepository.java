package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.entity.Expense;
import tr.com.everva.garage.model.entity.Vehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    List<Expense> findAllByVehicle(String vehicleId);

    @Query("select sum(e.money) from Expense e where e.vehicle.id = :vehicle")
    double sumAllExpensesByVehicle(@Param("vehicle") int vehicleId);

}
