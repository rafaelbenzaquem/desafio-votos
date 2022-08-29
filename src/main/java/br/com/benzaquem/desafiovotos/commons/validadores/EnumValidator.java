package br.com.benzaquem.desafiovotos.commons.validadores;

import br.com.benzaquem.desafiovotos.commons.validadores.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<Enum, String> {

    private String [] values;
    private boolean ignoreCase;

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.values = constraintAnnotation.values();
        this.ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(values).anyMatch(v -> ignoreCase ? v.equalsIgnoreCase(value) : v.equals(value));
    }
}
