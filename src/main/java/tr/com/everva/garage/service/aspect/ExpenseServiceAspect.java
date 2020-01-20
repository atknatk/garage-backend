package tr.com.everva.garage.service.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import tr.com.everva.garage.service.ExpenseService;
import tr.com.everva.garage.util.GalleryContext;

@Log4j2
@Aspect
@Component
public class ExpenseServiceAspect {

    @Before("execution(* tr.com.everva.garage.service.ExpenseService.*(..))  && target(expenseService)")
    public void aroundExecution(JoinPoint pjp, ExpenseService expenseService) {
        org.hibernate.Filter filter = expenseService.entityManager.unwrap(Session.class).enableFilter("galleryFilter");
        filter.setParameter("galleryId", GalleryContext.getCurrentGallery());
        filter.validate();
    }
}
