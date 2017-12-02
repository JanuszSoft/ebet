package pl.januszsoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class UserResultEntity extends AbstractEntity {

    private String username;

    @OneToOne(orphanRemoval = true)
    private PointsEntity pointsEntity = new PointsEntity();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_result_id")
    private LeagueUsersResultsEntity leagueResults;

    public UserResultEntity(String username, LeagueUsersResultsEntity leagueResults) {
        this.username = username;
        this.leagueResults = leagueResults;
    }
}
