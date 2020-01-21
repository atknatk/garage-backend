package tr.com.everva.garage.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tr.com.everva.garage.repository.SmsVerificationRepository;

import java.util.Calendar;
import java.util.Date;

@Service
public class SmsVerificationService {


    private final SmsVerificationRepository smsVerificationRepository;

    public SmsVerificationService(SmsVerificationRepository smsVerificationRepository) {
        this.smsVerificationRepository = smsVerificationRepository;
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void removeOldVerificationCode() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        smsVerificationRepository.deleteByCreatedAtLessThan(cal.getTime());

    }


}
