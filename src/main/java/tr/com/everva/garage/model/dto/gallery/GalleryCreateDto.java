package tr.com.everva.garage.model.dto.gallery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tr.com.everva.garage.model.dto.BaseIdDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderCreateFromGalleryDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;

import java.util.List;

@Getter
@Setter
public class GalleryCreateDto extends BaseIdDto {

    @JsonProperty("n")
    private String name;

    @JsonProperty("sl")
    private List<ShareHolderCreateFromGalleryDto> shareHolderDtoList;

    @JsonProperty("d")
    private String description;


}
