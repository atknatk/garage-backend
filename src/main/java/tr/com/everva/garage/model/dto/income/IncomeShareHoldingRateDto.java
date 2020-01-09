package tr.com.everva.garage.model.dto.income;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeShareHoldingRateDto {

    @JsonProperty("u")
    private String user;

    @JsonProperty("sh")
    private int shareHolding;

}
