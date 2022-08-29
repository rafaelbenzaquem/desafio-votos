package br.com.benzaquem.desafiovotos.commons.validadores;

import br.com.benzaquem.desafiovotos.commons.validadores.annotation.NotExistDB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotExistDBValidator implements ConstraintValidator<NotExistDB, Object> {

    private Class<?> domainClass;
    private String fieldName;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(NotExistDB constraint) {
        this.domainClass = constraint.domainClass();
        this.fieldName = constraint.fieldName();
    }


    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext constraintValidatorContext) {
        Query q = em.createQuery("select d from " + domainClass.getSimpleName() + " d where " + fieldName + "=:valor");
        q.setParameter("valor", valor);
        List<?> list = q.getResultList();
        return !list.isEmpty();
    }
}
