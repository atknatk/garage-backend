package tr.com.everva.garage.model.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.validation.ValidYear;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class VehicleCreateDto {

    @NotNull
    @JsonProperty("c")
    private String category;

    @ValidYear
    @JsonProperty("y")
    private String year;

    @JsonProperty("cl")
    private String color;

    @Min(value = 0L, message = "must be positive")
    @Max(value = 10000000L, message = "max value is 10000000")
    @JsonProperty("k")
    private int km;

    @JsonProperty("n")
    private String note;

    @JsonProperty("bd")
    private Date boughtAt;

    @Min(value = 0L, message = "must be positive")
    @JsonProperty("bp")
    private double buyingPrice;

}
