package tr.com.everva.garage.model.dto.shareholder;

import lombok.*;
import tr.com.everva.garage.model.dto.BaseIdDto;
import tr.com.everva.garage.model.entity.ShareHolder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareHolderDto extends BaseIdDto {

    private String userId;

    private String galleryId;

    private int shareHolding;

    public ShareHolderDto(ShareHolder shareHolder) {
        setId(shareHolder.getId());
        setShareHolding(shareHolder.getShareHolding());
        if(shareHolder.getGallery() != null){
            setGalleryId(shareHolder.getGallery().getId());
        }
        if(shareHolder.getUser() != null) {
            setUserId(shareHolder.getId());
        }
    }
}
