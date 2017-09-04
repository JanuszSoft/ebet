package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class BetEntity extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchEntity match;

    @Column
    private MatchResult betResult;

    @Column
    private String username;

    public BetEntity() {
    }

    public BetEntity(MatchEntity match, MatchResult betResult, String username) {
        this.match = match;
        this.betResult = betResult;
        this.username = username;
    }

    public long getMatchId(){
        return match.getId();
    }
}