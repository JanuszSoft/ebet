package pl.januszsoft.feature.bet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.januszsoft.entity.BetEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface BetRepository extends CrudRepository<BetEntity, Long> {

    Optional<BetEntity> findOne(long id);

    List<BetEntity> getAllByUsernameAndActiveTrue(String username);

}
