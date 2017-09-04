package pl.januszsoft.feature.businessObjects;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public abstract class BusinessObject<T extends Identifiable<K>,K extends Serializable> {

    private T entity;

    private Class<T> clazz;

    protected EntityManager entityManager;

    public BusinessObject(@NotNull T entity, Class<T> clazz,EntityManager entityManager) {
        this.entity = entity;
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    public T attached() {
        return entityManager.find(clazz,entity.getId());
    }

    public K getId(){
        return attached().getId();
    }
}
