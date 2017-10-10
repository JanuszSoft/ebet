package pl.januszsoft.feature.bet;

import org.springframework.stereotype.Service;
import pl.januszsoft.application.utils.Finder;
import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.feature.league.League;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class BetFinder implements Finder<Bet,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Bet> find(Long id) {
        BetEntity betEntity = entityManager.find(BetEntity.class, id);
        if(betEntity !=null && betEntity.isActive()){
            return Optional.of(new Bet(betEntity,entityManager));
        }
        return Optional.empty();
    }

    @Override
    public boolean exist(Long id) {
        return entityManager.find(LeagueEntity.class,id) != null;
    }

}
