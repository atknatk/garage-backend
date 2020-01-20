package tr.com.everva.garage.model.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.enums.ExpenseEnum;
import tr.com.everva.garage.model.dto.BaseIdDto;
import tr.com.everva.garage.model.entity.Expense;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto extends BaseIdDto {

    @NotNull
    private String expenseType;

    @NotNull
    private double money;

    @NotNull
    private String vehicleId;

    public ExpenseDto(Expense expense) {
        setId(expense.getId());
        setExpenseType(expense.getExpenseType().name());
        setMoney(expense.getMoney());
        setVehicleId(expense.getVehicle().getId());
    }
}
