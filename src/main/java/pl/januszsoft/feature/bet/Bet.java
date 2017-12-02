package pl.januszsoft.feature.bet;

import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;

public class Bet extends BusinessObject<BetEntity>{

    public Bet(BetEntity entity, EntityManager entityManager) {
        super(entity, BetEntity.class, entityManager);
    }

    public MatchResult getBetResult() {
        return attached().getBetResult();
    }

    public String getUsername() {
        return attached().getUsername();
    }
}
