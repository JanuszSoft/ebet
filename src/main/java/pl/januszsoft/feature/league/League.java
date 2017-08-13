package pl.januszsoft.feature.league;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;
import pl.januszsoft.feature.round.Round;

public class League extends BusinessObject<LeagueEntity,Long> {


    public League(LeagueEntity entity, CrudRepository<LeagueEntity, Long> repository) {
        super(entity, repository);
    }


    public int getRoundsNumber(){
        return attached().getRoundEntities().size();
    }

    public long addRound(Round round){
        RoundEntity roundEntity = round.attached();
        attached().addRound(roundEntity);
        return getRoundsNumber();
    }


}
