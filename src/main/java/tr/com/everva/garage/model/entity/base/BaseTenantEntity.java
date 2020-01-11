package tr.com.everva.garage.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import tr.com.everva.garage.model.IEntity;
import tr.com.everva.garage.model.entity.Tenant;
import tr.com.everva.garage.util.TenantContext;

import javax.persistence.*;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class BaseTenantEntity extends BaseEntity implements IEntity {


    @ManyToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
    protected Tenant tenant;


    @Column(name = "tenant_id")
    protected String tenantId;

    @PrePersist
    protected void onCreate() {
        String currentTenant = TenantContext.getCurrentTenant();
        Tenant tenant = new Tenant();
        tenant.setId(currentTenant);
        setTenant(tenant);
        setTenantId(currentTenant);
    }


}
