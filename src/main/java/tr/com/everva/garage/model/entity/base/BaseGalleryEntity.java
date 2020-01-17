package tr.com.everva.garage.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import tr.com.everva.garage.model.IEntity;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.util.GalleryContext;

import javax.persistence.*;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FilterDef(name = "galleryFilter", parameters = {@ParamDef(name = "galleryId", type = "string")})
@Filter(name = "galleryFilter", condition = "gallery_id = :galleryId")
public class BaseGalleryEntity extends BaseEntity implements IEntity {


    @ManyToOne
    @JoinColumn(name = "gallery_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Gallery gallery;


    @Column(name = "gallery_id")
    protected String galleryId;

    @PrePersist
    protected void onCreate() {
        String currentGallery = GalleryContext.getCurrentGallery();
        Gallery gallery = new Gallery();
        gallery.setId(currentGallery);
        setGallery(gallery);
        setGalleryId(currentGallery);
    }


}
