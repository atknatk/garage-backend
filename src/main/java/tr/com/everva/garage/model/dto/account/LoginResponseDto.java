package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.dto.account.UserDto;

@Getter
@Setter
public class LoginResponseDto {

    @JsonProperty("t")
    private final String jwtToken;
    @JsonProperty("u")
    private final UserDto user;

    public LoginResponseDto(String jwtToken, UserDto user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }
}
