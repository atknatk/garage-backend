package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.IGallery;
import tr.com.everva.garage.model.entity.base.BaseGalleryAuditEntity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShareHolder extends BaseGalleryAuditEntity implements IGallery, IAuditEntity {

    @ManyToOne
    @JoinColumn(name = "share_holder_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_share_holder_user_id"))
    private User user;

    // Sermaye Orani ya da Sermayesi Bedeli
    private int shareHolding;

}
