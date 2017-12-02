package pl.januszsoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;
import pl.januszsoft.feature.bet.BetDTO;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class MatchEntity extends AbstractEntity {

    private String host;

    private String guest;

    private MatchResult result = MatchResult.NOT_SET;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="round_id")
    @Where(clause = "active=true")
    private RoundEntity roundEntity;

    @OneToMany(mappedBy = "match",orphanRemoval = true)
    @Where(clause = "active=true")
    private Set<BetEntity> bets;

    public void addBet(BetEntity betEntity) {
        bets.add(betEntity);
    }

    public BetEntity addBet(BetDTO bet){
        BetEntity betEntity = new BetEntity();
        betEntity.setMatch(this);
        betEntity.setUsername(bet.getUsername());
        betEntity.setBetResult(bet.getResult());
        addBet(betEntity);
        return betEntity;
    }


}
