package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
public class BetEntity extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchEntity match;

    private MatchResult betResult;

    private String username;

}