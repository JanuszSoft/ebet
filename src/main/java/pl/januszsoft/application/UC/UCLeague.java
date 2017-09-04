package pl.januszsoft.application.UC;

import pl.januszsoft.feature.league.LeagueDTO;

import java.util.List;

public interface UCLeague {

    long createLeague(String name);
    void removeLeague(long id);
    LeagueDTO getLeagueDTO(long id);
    boolean isLeagueExist(long id);
    List<LeagueDTO> getAllLeagues();

}
