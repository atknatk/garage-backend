package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.entity.base.BaseAuditEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tenant extends BaseAuditEntity {

    private String galleryName;

    private String galleryPhone;

    private String galleryAddress;

    private boolean deleted;
}
