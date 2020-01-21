package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.model.entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByPhone(String phone);

    @Query("select u from User u where u.phone = :phone")
    Optional<User> findByPhone(@Param("phone") String phone);

    @Query("select u from User u  where u.phone in :phones")
    List<User> findByPhoneList(@Param("phones") List<String> phones);

    @Query("select u from User u left join fetch u.galleries where u.id in :id")
    Optional<User> findByIdFetchGallery(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("update User u set u.verify = true, u.active= true where u.id = :userId ")
    void verify(@Param("userId") String userId);

    @Query(value = "select 1 from gallery_user gu where gu.user_id = :user and gu.gallery_id = :gallery", nativeQuery = true)
    Optional<Integer> checkOwnGallery(@Param("user") String user, @Param("gallery") String gallery);

}
