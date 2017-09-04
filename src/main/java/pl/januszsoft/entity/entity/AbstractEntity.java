package pl.januszsoft.entity.entity;

import lombok.Data;
import pl.januszsoft.feature.businessObjects.Identifiable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class AbstractEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private boolean active = true;

    public void deactivate(){
        active = false;
    }

}
