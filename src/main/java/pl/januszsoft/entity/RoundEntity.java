package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class RoundEntity extends AbstractEntity {

    @OneToMany(mappedBy = "roundEntity",orphanRemoval = true)
    private List<MatchEntity> matches = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private LeagueEntity leagueEntity;

    public void addMatch(MatchEntity matchEntity){
        matches.add(matchEntity);
    }

}

