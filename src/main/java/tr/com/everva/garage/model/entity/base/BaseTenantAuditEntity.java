package tr.com.everva.garage.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.entity.Tenant;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class BaseTenantAuditEntity extends BaseAuditEntity implements IAuditEntity {

    @Column(name = "tenant_id")
    protected String tenantId;

    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Tenant tenant;

}
