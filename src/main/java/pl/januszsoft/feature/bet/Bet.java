package pl.januszsoft.feature.bet;

import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;

public class Bet extends BusinessObject<BetEntity>{

    public Bet(BetEntity entity, EntityManager entityManager) {
        super(entity, BetEntity.class, entityManager);
    }
}
