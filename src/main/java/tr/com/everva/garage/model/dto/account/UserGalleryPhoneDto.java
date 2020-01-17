package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseDto;

@Getter
@Setter
@NoArgsConstructor
public class UserGalleryPhoneDto extends UserGalleryDto {

    @JsonProperty("p")
    private String phone;

    public UserGalleryPhoneDto(String phone, String userId, String galleryId) {
        super(userId, galleryId);
        this.phone = phone;
    }

}
