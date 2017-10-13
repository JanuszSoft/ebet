package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LeagueCRUDService {

    private final LeagueFinder leagueFinder;
    private final LeagueCreator leagueCreator;

    @Autowired
    public LeagueCRUDService(LeagueFinder leagueFinder, LeagueCreator leagueCreator) {
        this.leagueFinder = leagueFinder;
        this.leagueCreator = leagueCreator;
    }

    public void removeById(long id){
        leagueFinder.find(id).orElseThrow(() -> new ResourceNotFoundException("No League with id: " + id)).delete();
    }

    public League getLeagueById(long id) {
        return leagueFinder.find(id).orElseThrow(()->new ResourceNotFoundException("No League with id: "+id));
    }

    public List<League> getAllLeagues(){
        List<League> all = leagueFinder.findAll();
        return all;
    }

}
