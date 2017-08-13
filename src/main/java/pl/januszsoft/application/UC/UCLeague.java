package pl.januszsoft.application.UC;

import pl.januszsoft.feature.league.LeagueInfo;

import java.util.List;

public interface UCLeague {

    long createLeague(String name);
    void removeLeague(long id);
    LeagueInfo getLeagueInfo(long id);
    boolean isLeagueExist(long id);
    List<LeagueInfo> getAllLeagues();

}
