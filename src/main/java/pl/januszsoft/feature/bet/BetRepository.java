package pl.januszsoft.feature.bet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.januszsoft.entity.BetEntity;

@Repository
public interface BetRepository extends CrudRepository<BetEntity, Long> {

}
