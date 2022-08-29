package br.com.benzaquem.desafiovotos.commons.validadores.annotation;

import br.com.benzaquem.desafiovotos.commons.validadores.ExistDBValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistDBValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistDB {

    String message() default ("Existe um valor igual cadastrado na base de dados.");
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> domainClass();
    String fieldName();
}
