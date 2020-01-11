package tr.com.everva.garage.model.dto.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseDto;
import tr.com.everva.garage.model.dto.BaseIdDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TenantDto extends BaseIdDto {

    @JsonProperty("gn")
    private String galleryName;

}
