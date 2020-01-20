package tr.com.everva.garage.model.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.entity.Expense;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseAddDto {

    @NotNull
    private String expenseType;

    @NotNull
    private double money;

}
