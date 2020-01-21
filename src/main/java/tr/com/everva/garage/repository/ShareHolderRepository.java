package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.enums.ConfigurationEnum;
import tr.com.everva.garage.model.entity.Configuration;
import tr.com.everva.garage.model.entity.ShareHolder;
import tr.com.everva.garage.model.entity.User;

import java.util.Optional;

@Repository
public interface ShareHolderRepository extends JpaRepository<ShareHolder, String> {

    Optional<ShareHolder> findByUser(User userId);

}
