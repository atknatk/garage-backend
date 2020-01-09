package tr.com.everva.garage.model.dto.shareholder;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import tr.com.everva.garage.model.dto.BaseDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareHolderDto extends BaseDto {

    private String userId;

    private int shareHolding;
}
