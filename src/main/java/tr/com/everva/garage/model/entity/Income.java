package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.IGallery;
import tr.com.everva.garage.model.entity.base.BaseGalleryAuditEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "unq_user_gallery_vehicle", columnNames = {"user_id", "vehicle_id", "gallery_id"})
)
public class Income extends BaseGalleryAuditEntity implements IGallery, IAuditEntity {

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
