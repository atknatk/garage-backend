package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.everva.garage.model.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, String> {
}
