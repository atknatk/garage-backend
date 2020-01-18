package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.dto.gallery.GalleryCreateDto;
import tr.com.everva.garage.model.entity.base.BaseAuditEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Gallery extends BaseAuditEntity {

    private String galleryName;

    private String galleryPhone;

    private String galleryAddress;


    private boolean deleted;

    @ManyToMany
    @JoinTable(
            name = "gallery_user",
            joinColumns = @JoinColumn(name = "gallery_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Gallery(String id) {
        setId(id);
    }

    public Gallery(GalleryCreateDto dto) {
        setId(dto.getId());
        setGalleryAddress(dto.getAddress());
        setGalleryName(dto.getName());
        setDeleted(false);
        setGalleryPhone(dto.getPhone());

    }
}
