package pl.januszsoft.feature.round;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.januszsoft.entity.RoundEntity;

import java.util.Optional;

@Repository
public interface RoundRepository extends CrudRepository<RoundEntity,Long>{

    Optional<RoundEntity> findOne(long id);

}
