package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseDto;

@Getter
@Setter
@NoArgsConstructor
public class UserGalleryDto extends BaseDto {

    @JsonProperty("u")
    private String userId;

    @JsonProperty("g")
    private String galleryId;

    public UserGalleryDto(String userId, String galleryId) {
        this.userId = userId;
        this.galleryId = galleryId;
    }

}
