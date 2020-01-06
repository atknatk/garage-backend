package tr.com.everva.gallery.service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import tr.com.everva.gallery.service.VehicleService;
import tr.com.everva.gallery.util.TenantContext;

@Log4j2
@Aspect
@Component
public class VehicleServiceAspect {

    @Before("execution(* tr.com.everva.gallery.service.VehicleService.*(..))  && target(vehicleService)")
    public void aroundExecution(JoinPoint pjp, VehicleService vehicleService) {
        org.hibernate.Filter filter = vehicleService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}