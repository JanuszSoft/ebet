package pl.januszsoft.feature.match;

import org.springframework.data.repository.CrudRepository;
import pl.januszsoft.entity.MatchEntity;

import java.util.Optional;

public interface MatchRepository extends CrudRepository<MatchEntity,Long> {

    Optional<MatchEntity> findOne(long id);

}
