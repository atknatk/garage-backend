package tr.com.everva.garage.validation;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

@Log4j2
public class YearValidator implements ConstraintValidator<ValidYear, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            int intYear = Integer.parseInt(value);
            return intYear > 1900 && intYear <= Calendar.getInstance().get(Calendar.YEAR) + 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
