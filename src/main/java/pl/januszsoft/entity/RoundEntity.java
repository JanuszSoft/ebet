package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class RoundEntity extends AbstractEntity {

    @OneToMany(mappedBy = "roundEntity")
    private List<MatchEntity> matches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private LeagueEntity leagueEntity;

    public RoundEntity() {
    }
}

