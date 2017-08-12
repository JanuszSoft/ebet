package pl.januszsoft.feature.league;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

public class League extends BusinessObject<LeagueEntity,Long> {

    public League(LeagueEntity entity, CrudRepository<LeagueEntity, Long> repository) {
        super(entity, repository);
    }





}
