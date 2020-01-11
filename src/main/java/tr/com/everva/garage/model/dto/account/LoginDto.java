package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class LoginDto {

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "(^$|[0-9]{12})") // 905325250522
    @JsonProperty("p")
    private String phone;

    @NotNull
    @Size(min = 6, max = 6)
    @JsonProperty("c")
    private String verificationCode;

}
