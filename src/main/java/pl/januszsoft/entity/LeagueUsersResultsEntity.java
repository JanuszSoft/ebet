package pl.januszsoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import pl.januszsoft.entity.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Where(clause = "active=true")
public class LeagueUsersResultsEntity extends AbstractEntity {

    @OneToMany(orphanRemoval = true, mappedBy = "leagueResults")
    @Where(clause = "active=true")
    private Set<UserResultEntity> userResultEntities = new HashSet<>();

}
