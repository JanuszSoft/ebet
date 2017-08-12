package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.LeagueEntity;

@Service
public class LeagueCreator {

    private LeagueRepository leagueRepository;

    @Autowired
    public LeagueCreator(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public League createLeague(String name) {
        LeagueEntity leagueEntity = createAndSaveEntity(name);
        League league = new League(leagueEntity,leagueRepository);
        return league;
    }

    private LeagueEntity createAndSaveEntity(String name) {
        LeagueEntity leagueEntity = new LeagueEntity(name);
        leagueRepository.save(leagueEntity);
        return leagueEntity;
    }

}
