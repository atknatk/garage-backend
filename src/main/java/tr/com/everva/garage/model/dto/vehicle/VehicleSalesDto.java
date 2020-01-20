package tr.com.everva.garage.model.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.model.dto.income.IncomeShareHoldingRateDto;
import tr.com.everva.garage.validation.ValidYear;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
public class VehicleSalesDto {

    @Min(value = 1000L, message = "must be positive")
    @JsonProperty("sp")
    private double soldPrice;

    @JsonProperty("shr")
    private List<IncomeShareHoldingRateDto> shareHoldingRates;


}
