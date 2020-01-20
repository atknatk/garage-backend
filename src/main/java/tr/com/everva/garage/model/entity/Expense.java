package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import tr.com.everva.garage.enums.ExpenseEnum;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.IGallery;
import tr.com.everva.garage.model.dto.expense.ExpenseAddDto;
import tr.com.everva.garage.model.entity.base.BaseGalleryAuditUserEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseGalleryAuditUserEntity implements IGallery, IAuditEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseEnum expenseType;

    @Column(precision = 8, scale = 2)
    @Type(type = "big_decimal")
    private double money;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false, foreignKey = @ForeignKey(name = "fk_expense_vehicle_id"))
    private Vehicle vehicle;

    public Expense(ExpenseAddDto dto) {
        setExpenseType(ExpenseEnum.valueOf(dto.getExpenseType()));
        setMoney(dto.getMoney());
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
