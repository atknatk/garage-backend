package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
}
