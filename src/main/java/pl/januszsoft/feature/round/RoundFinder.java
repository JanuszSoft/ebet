package pl.januszsoft.feature.round;

import org.springframework.stereotype.Service;
import pl.januszsoft.application.utils.Finder;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.match.Match;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class RoundFinder implements Finder<Round,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Round> find(Long id) {
        RoundEntity roundEntity = entityManager.find(RoundEntity.class,id);
        if(roundEntity!=null && roundEntity.isActive()){
            return Optional.of(new Round(roundEntity,entityManager));
        }
        return Optional.empty();
    }

    @Override
    public boolean exist(Long id) {
        return entityManager.find(RoundEntity.class,id) != null;
    }
}