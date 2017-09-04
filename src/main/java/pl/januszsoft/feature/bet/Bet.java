package pl.januszsoft.feature.bet;

import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;

public class Bet extends BusinessObject<BetEntity,Long>{

    public Bet(BetEntity entity, Class<BetEntity> clazz, EntityManager entityManager) {
        super(entity, clazz, entityManager);
    }
}
