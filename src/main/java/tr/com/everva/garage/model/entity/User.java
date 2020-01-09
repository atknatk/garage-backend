package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditEntity;

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
@Table(name = "users")
//@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
//@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class User extends BaseTenantAuditEntity implements ITenant {

    public User(String id) {
        this.setId(id);
    }

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String mail;

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "(^$|[0-9]{10})") // 905325250522
    private String phone;

}
