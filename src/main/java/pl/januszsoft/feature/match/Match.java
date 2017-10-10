package pl.januszsoft.feature.match;

import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.bet.Bet;
import pl.januszsoft.feature.bet.BetDTO;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class Match extends BusinessObject<MatchEntity> {

    public Match(MatchEntity entity, EntityManager entityManager) {
        super(entity, MatchEntity.class, entityManager);
    }

    @Transactional
    public Bet addBet(BetDTO betDTO){
        BetEntity entity = new BetEntity();
        entity.setBetResult(betDTO.getResult());
        entity.setUsername(betDTO.getUsername());
        entity.setMatch(attached());
        entityManager.persist(entity);
        return new Bet(entity,entityManager);
    }

    @Transactional
    public Bet updateBet(BetDTO betDTO) {
        long id = betDTO.getBetId();
        BetEntity entity = attached().getBets().stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("No bet with id: " + id));
        entity.setBetResult(betDTO.getResult());
        entity.setUsername(betDTO.getUsername());
        entityManager.persist(entity);
        return new Bet(entity,entityManager);
    }
}
