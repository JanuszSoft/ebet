package pl.januszsoft.feature.round;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;

public class Round extends BusinessObject<RoundEntity,Long> {

    public Round(RoundEntity entity, CrudRepository<RoundEntity, Long> repository) {
        super(entity, repository);
    }


}
