package pl.januszsoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class LeagueEntity extends AbstractEntity {


    private String name;

    @OneToMany(mappedBy = "leagueEntity",orphanRemoval = true)
    @Where(clause = "active=true")
    private Set<RoundEntity> roundEntities = new HashSet<>();

    public LeagueEntity(String name){
        this.name = name;
    }

    public void addRound(RoundEntity roundEntity) {
        roundEntities.add(roundEntity);
    }

}
