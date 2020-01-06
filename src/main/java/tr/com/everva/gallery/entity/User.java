package tr.com.everva.gallery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class User extends BaseEntity implements ITenant {

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String tenantId;

}
