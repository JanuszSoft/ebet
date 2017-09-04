package pl.januszsoft.feature.league;

import org.springframework.stereotype.Service;
import pl.januszsoft.application.utils.Finder;
import pl.januszsoft.entity.LeagueEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class LeagueFinder implements Finder<League,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<League> find(Long id) {
        LeagueEntity leagueEntity = entityManager.find(LeagueEntity.class,id);
        if(leagueEntity !=null){
            return Optional.of(new League(leagueEntity,entityManager));
        }
        return Optional.empty();
    }

    @Override
    public boolean exist(Long id) {
        return entityManager.find(LeagueEntity.class,id) != null;
    }
}
