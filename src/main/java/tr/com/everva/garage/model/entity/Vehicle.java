package tr.com.everva.garage.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.dto.vehicle.VehicleUpdateDto;
import tr.com.everva.garage.model.entity.base.BaseAuditEntity;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditEntity;
import tr.com.everva.garage.validation.ValidYear;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

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

    private int km;

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

    @OneToMany(mappedBy = "vehicle")
    private List<Expense> expenses;


    public void merge(VehicleUpdateDto vehicleUpdateDto) {
        this.setYear(vehicleUpdateDto.getYear());
        this.setColor(vehicleUpdateDto.getColor());
        this.setKm(vehicleUpdateDto.getKm());
        this.setNote(vehicleUpdateDto.getNote());
        this.setBuyingPrice(vehicleUpdateDto.getBuyingPrice());
    }


}
