package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Income extends BaseTenantAuditEntity implements ITenant, IAuditEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_income_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", foreignKey = @ForeignKey(name = "fk_income_vehicle_id"))
    private Vehicle vehicle;

    @Column(precision = 8, scale = 2) // kazanç
    private double gain;

    @OneToOne(mappedBy = "income", cascade = CascadeType.ALL)
    private IncomeNote incomeNote;

}
