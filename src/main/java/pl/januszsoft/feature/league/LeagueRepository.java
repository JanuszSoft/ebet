package pl.januszsoft.feature.league;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.LeagueEntity;

import java.util.Optional;

public interface LeagueRepository extends CrudRepository<LeagueEntity,Long>{

    void deleteByName(String name);
    Optional<LeagueEntity> findByName(String name);

}
