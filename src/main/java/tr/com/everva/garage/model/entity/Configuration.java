package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.IGallery;
import tr.com.everva.garage.model.entity.base.BaseGalleryAuditUserEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Configuration extends BaseGalleryAuditUserEntity implements IAuditEntity, IGallery {

    @Enumerated(EnumType.STRING)
    private ConfigurationEnum key;

    private String value;

}
