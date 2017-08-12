package pl.januszsoft.entity;

import lombok.Data;
import pl.januszsoft.feature.businessObjects.Identifiable;
import pl.januszsoft.feature.league.League;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class LeagueEntity implements Identifiable<Long>{

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @OneToMany
    private List<RoundEntity> roundEntities;

    public LeagueEntity(String name){
        this.name = name;
    }

}
