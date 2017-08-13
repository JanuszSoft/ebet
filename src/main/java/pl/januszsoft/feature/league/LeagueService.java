package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.LeagueEntity;

import java.util.Optional;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;
    @Autowired
    private final LeagueCreator leagueCreator;


    @Autowired
    public LeagueService(LeagueRepository leagueRepository, LeagueCreator leagueCreator) {
        this.leagueRepository = leagueRepository;
        this.leagueCreator = leagueCreator;
    }

    public Optional<League> getLeagueById(long id) {
        Optional<LeagueEntity> leagueEntity = leagueRepository.findOne(id);
        if(leagueEntity.isPresent()){
            final LeagueEntity entity = leagueEntity.get();
            return Optional.of(new League(entity,leagueRepository));
        }

        return Optional.empty();
    }
}
