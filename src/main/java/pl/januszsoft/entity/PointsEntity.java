package pl.januszsoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class PointsEntity extends AbstractEntity {

    private int counter = 0;

}
