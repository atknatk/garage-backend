package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.entity.Income;
import tr.com.everva.garage.model.entity.ShareHolder;

@Repository
public interface IncomeRepository extends JpaRepository<Income, String> {

}
