package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.ITenant;
import tr.com.everva.garage.model.entity.base.BaseTenantAuditEntity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShareHolder extends BaseTenantAuditEntity implements ITenant, IAuditEntity {

    @ManyToOne
    @JoinColumn(name = "share_holder_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_share_holder_user_id"))
    private User user;

    // Sermaye Orani ya da Sermayesi Bedeli
    private int shareHolding;

}
