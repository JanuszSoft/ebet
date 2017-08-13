package pl.januszsoft.entity;

import lombok.Data;
import pl.januszsoft.feature.businessObjects.Identifiable;
import pl.januszsoft.feature.league.League;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class RoundEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "roundEntity")
    private List<MatchEntity> matches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private LeagueEntity leagueEntity;

    public RoundEntity() {
    }
}
