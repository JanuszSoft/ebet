package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class LeagueEntity extends AbstractEntity {


    private String name;

    @OneToMany(mappedBy = "leagueEntity",orphanRemoval = true)
    private List<RoundEntity> roundEntities = new ArrayList<>();


    public LeagueEntity(String name){
        this.name = name;
    }

    public void addRound(RoundEntity roundEntity) {
        roundEntities.add(roundEntity);
    }

}
