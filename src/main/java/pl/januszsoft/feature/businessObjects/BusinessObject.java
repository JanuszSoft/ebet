package pl.januszsoft.feature.businessObjects;

import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;


public abstract class BusinessObject<T extends AbstractEntity> {

    private T entity;

    private Class<T> clazz;

    protected EntityManager entityManager;

    public BusinessObject(@NotNull T entity, Class<T> clazz,EntityManager entityManager) {
        this.entity = entity;
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    @Transactional
    public T attached() {
        return entityManager.find(clazz,entity.getId());
    }

    public void delete(){
        attached().deactivate();
    }

    public long getId(){
        return attached().getId();
    }

    protected int convertHumanNumberToITNumber(long n) {
        return (int)n-1;
    }
}
