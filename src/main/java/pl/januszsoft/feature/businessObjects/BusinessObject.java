package pl.januszsoft.feature.businessObjects;

import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public abstract class BusinessObject<T extends Identifiable<K>,K extends Serializable> {

    @NotNull
    protected T entity;

    @NotNull
    protected CrudRepository<T,K> repository;

    public BusinessObject(T entity,CrudRepository<T,K> repository) {
        this.entity = entity;
        this.repository = repository;
    }

    public T attached() {
        return repository.findOne(entity.getId());
    }

    public K getId(){
        return entity.getId();
    }

}
