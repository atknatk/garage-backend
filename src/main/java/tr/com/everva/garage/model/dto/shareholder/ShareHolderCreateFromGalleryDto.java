package tr.com.everva.garage.model.dto.shareholder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseIdDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareHolderCreateFromGalleryDto extends BaseIdDto {

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "(^$|[^+].+|[0-9]{12})") // 905325250522
    @JsonProperty("p")
    private String phone;

    private int shareHolding;
}
