package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.everva.garage.model.entity.SmsVerification;

import java.util.Optional;

public interface SmsVerificationRepository extends JpaRepository<SmsVerification, String> {

    Optional<SmsVerification> findByPhone(String phone);

}
