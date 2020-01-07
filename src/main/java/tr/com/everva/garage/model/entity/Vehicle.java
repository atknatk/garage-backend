package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.entity.base.BaseAuditEntity;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
//@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Vehicle extends BaseTenantAuditEntity implements ITenant, IAuditEntity {

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_vehicle_category_id"))
    private Category category;

    private String year;

    private String color;

    private String sahibindenId;

    @Column(columnDefinition = "TEXT")
    private String note;

    private Date soldAt;

    private Date boughtAt;

    private boolean sold;

    @Column(precision = 12, scale = 2)
    private double buyingPrice;

    @Column(precision = 12, scale = 2)
    private double salesPrice;


}
