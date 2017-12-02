package pl.januszsoft.feature.leagueUsersResults;

import pl.januszsoft.entity.LeagueUsersResultsEntity;
import pl.januszsoft.entity.UserResultEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class LeagueUsersResult extends BusinessObject<LeagueUsersResultsEntity> {

    public LeagueUsersResult(LeagueUsersResultsEntity entity, EntityManager entityManager) {
        super(entity, LeagueUsersResultsEntity.class, entityManager);
    }

    public void addPointToUser(String username) {
        UserResult userResult = createOrGetUserResult(username);
        userResult.addOnePoints();
    }

    private UserResult createOrGetUserResult(String username) {
        return getUserResultWithUsername(username).orElse(addUserResult(username));
    }


    public Optional<UserResult> getUserResultWithUsername(String username) {
        return getAllUsersResult().stream().filter(e -> e.hasUserName(username)).findFirst();
    }

    public boolean hasUserWithUsername(String username) {
        return getAllUsersResult().stream().anyMatch(e -> e.hasUserName(username));
    }

    public UserResult addUserResult(String username) {
        UserResultEntity entity = new UserResultEntity(username, attached());
        entityManager.persist(entity);
        return new UserResult(entity, entityManager);
    }

    public Set<UserResult> getAllUsersResult() {
        return attached().getUserResultEntities().stream().map(e -> new UserResult(e, entityManager)).collect(Collectors.toSet());
    }
}
