package pl.januszsoft.feature.round;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.januszsoft.entity.RoundEntity;

@Repository
public interface RoundRepository extends CrudRepository<RoundEntity,Long>{
}
