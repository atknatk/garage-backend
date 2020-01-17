package tr.com.everva.garage.model.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.everva.garage.model.IAuditEntity;
import tr.com.everva.garage.model.entity.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseGalleryAuditUserEntity extends BaseGalleryEntity implements IAuditEntity {

    @Column(name = "created_at")
    protected Date createdAt;

    @Column(name = "updated_at")
    protected Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_user_id", referencedColumnName = "id")
    private User updatedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        createdBy = (User) principal;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        updatedBy = (User) principal;
    }
}
