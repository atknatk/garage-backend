package tr.com.everva.garage.model.dto.shareholder;

import lombok.*;
import tr.com.everva.garage.model.dto.BaseIdDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareHolderDto extends BaseIdDto {

    private String userId;

    private int shareHolding;
}
