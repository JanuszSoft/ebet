package pl.januszsoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class RoundEntity extends AbstractEntity {

    @OneToMany(mappedBy = "roundEntity",orphanRemoval = true)
    @Where(clause = "active=true")
    private Set<MatchEntity> matches = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private LeagueEntity leagueEntity;

    public void addMatch(MatchEntity matchEntity){
        matches.add(matchEntity);
    }


}

