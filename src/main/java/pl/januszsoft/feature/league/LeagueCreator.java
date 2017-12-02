package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.LeagueUsersResultsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class LeagueCreator {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public LeagueCreator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public League createLeague(String name) {
        LeagueEntity leagueEntity = new LeagueEntity(name);
        LeagueUsersResultsEntity leagueUsersResultsEntity = new LeagueUsersResultsEntity();
        entityManager.persist(leagueUsersResultsEntity);
        leagueEntity.setLeagueUsersResultsEntity(leagueUsersResultsEntity);
        entityManager.persist(leagueEntity);
        return new League(leagueEntity,entityManager);
    }

}
