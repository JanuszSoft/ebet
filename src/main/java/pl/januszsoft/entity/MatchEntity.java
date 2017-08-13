package pl.januszsoft.entity;

import lombok.Data;
import pl.januszsoft.feature.businessObjects.Identifiable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table
public class MatchEntity implements Identifiable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String host;

    @Column
    private String guest;

    @Column
    private MatchResult result;

    @OneToMany(mappedBy = "id")
    private List<BetEntity> betList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="round_id")
    private RoundEntity roundEntity;

    @OneToMany(mappedBy = "match")
    private Set<BetEntity> bets;

    public MatchEntity() {
    }
}
