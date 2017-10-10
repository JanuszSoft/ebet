package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.januszsoft.entity.entity.AbstractEntity;
import pl.januszsoft.feature.bet.BetDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class MatchEntity extends AbstractEntity {

    private String host;

    private String guest;

    private MatchResult result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="round_id")
    private RoundEntity roundEntity;

    @OneToMany(mappedBy = "match",orphanRemoval = true)
    private List<BetEntity> bets;

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
