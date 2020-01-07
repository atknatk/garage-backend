package tr.com.everva.garage.model;

import java.util.Date;

public interface IAuditEntity extends IEntity {

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);
}
