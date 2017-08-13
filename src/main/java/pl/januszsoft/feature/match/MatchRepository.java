package pl.januszsoft.feature.match;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.MatchEntity;

public interface MatchRepository extends CrudRepository<MatchEntity,Long> {
}
