package tr.com.everva.garage.model.dto.account;

import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseDto;

@Getter
@Setter
public class BasicUserDto extends BaseDto {

    private String id;

    private String phone;

    private String name;

    public BasicUserDto(UserDto principal) {
        setId(principal.getId());
        setPhone(principal.getPhone());
        setName(principal.getName());
    }
}
