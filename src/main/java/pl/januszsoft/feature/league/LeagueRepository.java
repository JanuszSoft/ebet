package pl.januszsoft.feature.league;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.januszsoft.entity.LeagueEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends CrudRepository<LeagueEntity,Long>{

    List<LeagueEntity> findAll();
    Optional<LeagueEntity> findOne(long id);
}
