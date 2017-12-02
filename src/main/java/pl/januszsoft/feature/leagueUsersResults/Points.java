package pl.januszsoft.feature.leagueUsersResults;

import pl.januszsoft.entity.PointsEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;

public class Points extends BusinessObject<PointsEntity> {

    public Points(PointsEntity entity, EntityManager entityManager) {
        super(entity, PointsEntity.class, entityManager);
    }

    public void addPoints(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("Points added should be positive");
        }
        int old = attached().getCounter();
        int newCounter = old + value;
        attached().setCounter(newCounter);
    }
}
