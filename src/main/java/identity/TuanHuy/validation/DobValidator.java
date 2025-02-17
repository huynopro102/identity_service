package identity.TuanHuy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {
    private int min;
    // this is method need implements two methods , press alt + shift + enter

    @Override
    // initialize to get values
    // the initialize method will occur before method isValid
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    // isValid is method handle data
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(Objects.isNull(value)){
            return true;
        }
        Long age = ChronoUnit.YEARS.between(value , LocalDate.now());
        return age >= min;
    }



}