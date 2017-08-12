package pl.januszsoft.application.UC;

import pl.januszsoft.feature.league.LeagueInfo;

public interface UCLeague {

    long createLeague(String name);
    void removeLeague(String name);
    LeagueInfo getLeagueInfo(String name);
    boolean isLeagueExist(String name);

}
