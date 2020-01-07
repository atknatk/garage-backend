package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import tr.com.everva.garage.enums.ExpenseEnum;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.entity.base.BaseAuditEntity;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditUserEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseTenantAuditUserEntity implements ITenant, IAuditEntity {

    @Enumerated(EnumType.STRING)
    private ExpenseEnum expenseType;

    @Column(precision = 8, scale = 2)
    @Type(type = "big_decimal")
    private double money;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
