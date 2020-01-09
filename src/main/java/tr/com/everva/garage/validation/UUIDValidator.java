package tr.com.everva.garage.validation;

import lombok.extern.log4j.Log4j2;
import tr.com.everva.garage.util.ValidatorUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

@Log4j2
public class UUIDValidator implements ConstraintValidator<ValidUUID, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidatorUtils.isValidUUID(value);
    }
}
