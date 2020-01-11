package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByPhone(String phone);

    @Query("select u from User u inner join fetch u.tenant where u.phone = :phone")
    Optional<User> findByPhone(@Param("phone") String phone);

}
