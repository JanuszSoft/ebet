package pl.januszsoft.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
public class BetEntity extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    @Where(clause = "active=true")
    private MatchEntity match;

    private MatchResult betResult;

    private String username;

}