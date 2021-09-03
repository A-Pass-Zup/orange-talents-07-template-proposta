package br.com.zupacademy.apass.microservicepropostas.validation.constraints;

import br.com.zupacademy.apass.microservicepropostas.validation.IsFutureLocalDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsFutureLocalDateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsFutureLocalDate {

    String message() default "{br.com.zupacademy.apass.casadocodigo.validation.constraint.isfuturedate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
