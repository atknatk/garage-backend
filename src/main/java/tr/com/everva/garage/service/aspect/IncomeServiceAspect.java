package tr.com.everva.garage.service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import tr.com.everva.garage.service.ConfigurationService;
import tr.com.everva.garage.service.IncomeService;
import tr.com.everva.garage.util.TenantContext;

@Log4j2
@Aspect
@Component
public class IncomeServiceAspect {

    @Before("execution(* tr.com.everva.garage.service.IncomeService.*(..))  && target(configurationService)")
    public void aroundExecution(JoinPoint pjp, IncomeService configurationService) {
        org.hibernate.Filter filter = configurationService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}
