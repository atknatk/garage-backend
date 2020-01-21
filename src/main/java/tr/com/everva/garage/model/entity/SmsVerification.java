package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SmsVerification extends BaseEntity {

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "(^$|[0-9]{12})") // 905325250522
    @Column(unique = true, nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 6)
    private String code;

    @Column(nullable = false)
    private Date createdAt;


}
