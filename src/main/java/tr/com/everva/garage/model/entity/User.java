package tr.com.everva.garage.model.entity;

import lombok.*;
import tr.com.everva.garage.model.IGallery;
import tr.com.everva.garage.model.entity.base.BaseGalleryAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
//@FilterDef(name = "galleryFilter", parameters = {@ParamDef(name = "galleryId", type = "string")})
//@Filter(name = "galleryFilter", condition = "gallery_id = :galleryId")
public class User extends BaseGalleryAuditEntity implements IGallery {

    public User(String id) {
        this.setId(id);
    }

    @NotNull
    private String name;

    private String mail;

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "(^$|[0-9]{12})") // 905325250522
    @Column(unique = true)
    private String phone;

    private boolean deleted;

    private boolean active;

}
