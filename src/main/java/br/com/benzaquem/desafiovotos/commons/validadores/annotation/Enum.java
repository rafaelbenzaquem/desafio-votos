package br.com.benzaquem.desafiovotos.commons.validadores.annotation;

import br.com.benzaquem.desafiovotos.commons.validadores.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {

    String message() default ("Valores inválidos.");
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] values();
    boolean ignoreCase() default false;
}
