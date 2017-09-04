package pl.januszsoft.feature.match;

import org.springframework.stereotype.Service;
import pl.januszsoft.application.utils.Finder;
import pl.januszsoft.entity.MatchEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class MatchFinder implements Finder<Match,Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Match> find(Long id) {
        MatchEntity matchEntity = entityManager.find(MatchEntity.class,id);
        if(matchEntity!=null){
            return Optional.of(new Match(matchEntity,entityManager));
        }
        return Optional.empty();
    }

    @Override
    public boolean exist(Long id) {
        return entityManager.find(MatchEntity.class,id) != null;
    }
}