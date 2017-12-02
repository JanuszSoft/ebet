package pl.januszsoft.feature.leagueUsersResults;

import pl.januszsoft.entity.PointsEntity;
import pl.januszsoft.entity.UserResultEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;
import java.util.Objects;

public class UserResult extends BusinessObject<UserResultEntity> {

    public UserResult(UserResultEntity entity, EntityManager entityManager) {
        super(entity, UserResultEntity.class, entityManager);
        PointsEntity pointsEntity = new PointsEntity();
        attached().setPointsEntity(pointsEntity);
        entityManager.persist(pointsEntity);
    }


    public void addOnePoints() {
        getUserResultPoints().addPoints(1);
    }

    public Points getUserResultPoints() {
        return new Points(attached().getPointsEntity(), entityManager);
    }


    public boolean hasUserName(String username) {
        return Objects.equals(attached().getUsername(), username);
    }
}
