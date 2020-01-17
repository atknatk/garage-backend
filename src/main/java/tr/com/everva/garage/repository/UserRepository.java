package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.dto.account.UserGalleryDto;
import tr.com.everva.garage.model.dto.account.UserGalleryPhoneDto;
import tr.com.everva.garage.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByPhone(String phone);

    @Query("select new tr.com.everva.garage.model.dto.account.UserGalleryDto(u.id,u.galleryId) from User u inner join fetch u.gallery where u.phone = :phone")
    Optional<UserGalleryDto> findByPhone(@Param("phone") String phone);

    @Query("select new tr.com.everva.garage.model.dto.account.UserGalleryPhoneDto(u.phone,u.id,u.galleryId) from User u inner join fetch u.gallery where u.phone in :phones")
    List<UserGalleryPhoneDto> findByPhoneList(@Param("phones") List<String> phones);

}
