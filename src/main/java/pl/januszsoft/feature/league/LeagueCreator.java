package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.LeagueEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class LeagueCreator {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public LeagueCreator(LeagueRepository leagueRepository, EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public League createLeague(String name) {
        LeagueEntity leagueEntity = new LeagueEntity(name);
        entityManager.persist(leagueEntity);
        return new League(leagueEntity,entityManager);
    }

}
