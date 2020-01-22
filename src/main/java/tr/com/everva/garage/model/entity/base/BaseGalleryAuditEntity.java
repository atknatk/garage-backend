package tr.com.everva.garage.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.entity.Gallery;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "galleryFilter", parameters = {@ParamDef(name = "galleryId", type = "string")})
@Filter(name = "galleryFilter", condition = "gallery_id = :galleryId")
public class BaseGalleryAuditEntity extends BaseAuditEntity implements IAuditEntity {

    @Column(name = "gallery_id")
    protected String galleryId;

    @ManyToOne
    @JoinColumn(name = "gallery_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    protected Gallery gallery;

}
