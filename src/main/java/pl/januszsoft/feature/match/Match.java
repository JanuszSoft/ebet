package pl.januszsoft.feature.match;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.feature.businessObjects.BusinessObject;
import pl.januszsoft.feature.businessObjects.Identifiable;

public class Match extends BusinessObject {

    public Match(MatchEntity entity, CrudRepository repository) {
        super(entity, repository);
    }


}
