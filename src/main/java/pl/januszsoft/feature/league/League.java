package pl.januszsoft.feature.league;

import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;
import pl.januszsoft.feature.round.Round;
import pl.januszsoft.feature.round.RoundDTO;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class League extends BusinessObject<LeagueEntity,Long> {


    public League(LeagueEntity entity, EntityManager entityManager) {
        super(entity, LeagueEntity.class, entityManager);
    }

    public int getRoundsNumber(){
        return attached().getRoundEntities().size();
    }

    @Transactional
    public Round addRound(RoundDTO roundDTO){
        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setLeagueEntity(attached());
        entityManager.persist(roundEntity);
        return new Round(roundEntity);
    }
}
