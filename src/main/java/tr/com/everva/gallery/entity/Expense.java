package tr.com.everva.gallery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import tr.com.everva.gallery.enums.ExpenseEnum;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ExpenseEnum expenseType;

    @Column(precision = 8, scale = 6)
    @Type(type = "big_decimal")
    private double money;

    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
