package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class LeagueEntity extends AbstractEntity {


    private String name;

    @OneToMany(mappedBy = "leagueEntity",orphanRemoval = true)
    @Where(clause = "active=true")
    private List<RoundEntity> roundEntities = new ArrayList<>();


    public LeagueEntity(String name){
        this.name = name;
    }

    public void addRound(RoundEntity roundEntity) {
        roundEntities.add(roundEntity);
    }

}
