package tr.com.everva.garage.model.dto.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseDto;
import tr.com.everva.garage.model.dto.BaseIdDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class GarageCreateDto extends BaseIdDto {

    @JsonProperty("n")
    private String name;

    @JsonProperty("sl")
    private List<ShareHolderDto> shareHolderDtoList;

    @JsonProperty("d")
    private String description;


}
