package tr.com.everva.garage.model.dto.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.entity.Expense;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseAddOrUpdateDto {

    @NotNull
    @JsonProperty("e")
    private String expenseType;

    @NotNull
    @JsonProperty("m")
    private BigDecimal money;

}
