package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.entity.base.BaseEntity;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditUserEntity;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Configuration extends BaseTenantAuditUserEntity implements IAuditEntity, ITenant {


    @Enumerated(EnumType.STRING)
    private ConfigurationEnum key;

    private String value;

}
