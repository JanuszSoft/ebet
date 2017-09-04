package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class LeagueEntity extends AbstractEntity {


    @Column
    private String name;

    @OneToMany(mappedBy = "leagueEntity")
    private List<RoundEntity> roundEntities;

    public LeagueEntity() {}

    public LeagueEntity(String name){
        this.name = name;
    }

    public void addRound(RoundEntity roundEntity) {
        roundEntities.add(roundEntity);
    }

}
