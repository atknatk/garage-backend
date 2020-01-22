package tr.com.everva.garage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.everva.garage.model.IEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IncomeNote implements IEntity {

    @Id
    @Column(name = "id")
    private String id;

    @OneToOne
    @MapsId
    private Income income;

    private String note;

}
