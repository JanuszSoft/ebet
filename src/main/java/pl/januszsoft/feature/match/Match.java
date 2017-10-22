package pl.januszsoft.feature.match;

import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.bet.Bet;
import pl.januszsoft.feature.bet.BetDTO;
import pl.januszsoft.feature.businessObjects.BusinessObject;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public class Match extends BusinessObject<MatchEntity> {

    public Match(MatchEntity entity, EntityManager entityManager) {
        super(entity, MatchEntity.class, entityManager);
    }

    public Bet addBet(BetDTO betDTO){
        BetEntity entity = new BetEntity();
        entity.setBetResult(betDTO.getResult());
        entity.setUsername(betDTO.getUsername());
        entity.setMatch(attached());
        entityManager.persist(entity);
        return new Bet(entity,entityManager);
    }

    public Bet updateBet(BetDTO betDTO) {
        long id = betDTO.getBetId();
        BetEntity entity = attached().getBets().stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("No bet with id: " + id));
        entity.setBetResult(betDTO.getResult());
        entity.setUsername(betDTO.getUsername());
        entityManager.persist(entity);
        return new Bet(entity,entityManager);
    }

    public void setResult(MatchResult result) {
        attached().setResult(result);
    }

    public void updateHost(String host) {
        attached().setHost(host);
    }

    public void updateGuest(String guest) {
        attached().setGuest(guest);
    }


}
