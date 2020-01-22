package tr.com.everva.garage.util;

import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import tr.com.everva.garage.model.entity.Vehicle;

import java.io.Serializable;
import java.util.Random;

@Log4j2
public class VehicleIdGenerator extends SequenceStyleGenerator {
    Random r = new Random();

    Session session;

    int attempt = 0;

    public int generate9DigitNumber() {
        int aNumber = (int) ((Math.random() * 900000000) + 100000000);
        return aNumber;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {
        this.session = (Session) session;
        Integer id = generateRandomIndex();
        return id;
    }

    public Integer generateRandomIndex() {
        for (int i = 0; i < 3; i++) {
            log.info("attempt: " + i);
            Integer a = generate9DigitNumber();

            log.info("index: " + a);
            boolean exists = (Long) session
                    .createQuery("select count(v) from Vehicle v where v.id = ?1")
                    .setParameter(1, a)
                    .uniqueResult() > 0;
            if (!exists) {
                log.info("not found this id");
                return a;
            } else {
                log.info("found this id");
            }
        }

        for (int i = 100000000; i < 999999999; i++) {
            log.info("Is id free: " + i);
            if (session.get(Vehicle.class, i) == null) {
                log.info("id is free: " + i);
                return i;
            }
        }
        return null;
    }
}
