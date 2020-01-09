package tr.com.everva.garage.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public LoginResponseDto(String jwttoken) {
        this.jwttoken = jwttoken;
    }

}
