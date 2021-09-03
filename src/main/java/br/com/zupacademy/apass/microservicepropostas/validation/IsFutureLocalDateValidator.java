package br.com.zupacademy.apass.microservicepropostas.validation;

import br.com.zupacademy.apass.microservicepropostas.validation.constraints.IsFutureLocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IsFutureLocalDateValidator implements ConstraintValidator<IsFutureLocalDate, LocalDate> {

    @Override
    public void initialize(IsFutureLocalDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.compareTo(LocalDate.now()) > 0;
    }
}
