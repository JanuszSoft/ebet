package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeagueCRUDService {

    private final LeagueFinder leagueFinder;
    private final LeagueRepository leagueRepository;
    private final LeagueCreator leagueCreator;

    @Autowired
    public LeagueCRUDService(LeagueFinder leagueFinder, LeagueRepository leagueRepository, LeagueCreator leagueCreator) {
        this.leagueFinder = leagueFinder;
        this.leagueRepository = leagueRepository;
        this.leagueCreator = leagueCreator;
    }

    public void removeById(long id){
        //leagueFinder.find(id).orElseThrow(()->new ResourceNotFoundException("No League with id: "+id)).delete();
        leagueRepository.delete(id);
    }

    public League getLeagueById(long id) {
        return leagueFinder.find(id).orElseThrow(()->new ResourceNotFoundException("No League with id: "+id));
    }

    public List<League> getAllLeagues(){
        List<LeagueEntity> all = leagueRepository.findAllByActiveTrue();
        return all.stream().map(leagueCreator::fromEntity).collect(Collectors.toList());
    }

}
