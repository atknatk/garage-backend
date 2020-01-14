package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VerifyDto extends BaseDto {

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "(^$|[0-9]{12})") // 905325250522
    @JsonProperty("p")
    private String phone;

    @NotNull
    @Size(min = 6, max = 6)
    @JsonProperty("c")
    private String code;
}
