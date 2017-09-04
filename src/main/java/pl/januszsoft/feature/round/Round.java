package pl.januszsoft.feature.round;

import org.springframework.beans.factory.annotation.Configurable;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;
import pl.januszsoft.feature.match.MatchDTO;

@Configurable
public class Round extends BusinessObject<RoundEntity,Long> {

    public Round(RoundEntity entity) {
        super(entity, RoundEntity.class,null); //TODO
    }


    private MatchEntity createMatchEntity(MatchDTO matchDTO) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setRoundEntity(attached());
        matchEntity.setHost(matchDTO.getHost());
        matchEntity.setGuest(matchDTO.getGuest());
        return matchEntity;
    }

}
