package pl.januszsoft.application.UC;

import pl.januszsoft.feature.league.LeagueDTO;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UCLeague {

    LeagueDTO createLeague(String name);
    void removeLeague(long id);
    LeagueDTO getLeagueDTO(long id);
    List<LeagueDTO> getAllLeagues();


}
