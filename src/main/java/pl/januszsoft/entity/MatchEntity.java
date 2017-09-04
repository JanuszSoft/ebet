package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.januszsoft.entity.entity.AbstractEntity;
import pl.januszsoft.feature.bet.BetDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class MatchEntity extends AbstractEntity {

    @Column
    private String host;

    @Column
    private String guest;

    @Column
    private MatchResult result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="round_id")
    private RoundEntity roundEntity;

    @OneToMany(mappedBy = "match")
    private List<BetEntity> bets;

    public MatchEntity() {
    }

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
