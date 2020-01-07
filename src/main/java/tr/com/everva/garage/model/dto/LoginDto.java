package tr.com.everva.garage.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150307L;
    private String phone;
    private String phoneCode;

}
