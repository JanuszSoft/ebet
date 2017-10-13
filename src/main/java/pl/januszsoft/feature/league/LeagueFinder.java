package pl.januszsoft.feature.league;

import org.springframework.stereotype.Service;
import pl.januszsoft.application.utils.Finder;
import pl.januszsoft.entity.LeagueEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeagueFinder implements Finder<League,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<League> find(Long id) {
        LeagueEntity leagueEntity = entityManager.find(LeagueEntity.class,id);
        if(leagueEntity !=null && leagueEntity.isActive()){
            return Optional.of(new League(leagueEntity,entityManager));
        }
        return Optional.empty();
    }

    @Override
    public boolean exist(Long id) {
        return entityManager.find(LeagueEntity.class,id) != null;
    }

    public List<League> findAll() {
        Query query = entityManager.createQuery("SELECT e from LeagueEntity e");
        return (List<League>) query.getResultList()
                .stream()
                .map(e -> new League((LeagueEntity) e, entityManager))
                .collect(Collectors.toList());
    }
}
