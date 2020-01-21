package tr.com.everva.garage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import tr.com.everva.garage.model.entity.SmsVerification;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public interface SmsVerificationRepository extends JpaRepository<SmsVerification, String> {

    Optional<SmsVerification> findByPhone(String phone);

    @Transactional
    @Modifying
    void deleteByCreatedAtLessThan(Date date);


}
