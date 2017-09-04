package pl.januszsoft.application.UC.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.*;

import java.util.List;

@Service
public class DefaultUCLeague implements UCLeague{

    private final LeagueCreator leagueCreator;
    private final LeagueService leagueService;
    private final LeagueFinder leagueFinder;


    @Autowired
    public DefaultUCLeague(LeagueCreator leagueCreator, LeagueService leagueService, LeagueFinder leagueFinder) {
        this.leagueCreator = leagueCreator;
        this.leagueService = leagueService;
        this.leagueFinder = leagueFinder;
    }


    @Override
    public long createLeague(String name) {
        League league = leagueCreator.createLeague(name);
        return league.getId();
    }

    @Override
    public void removeLeague(long id) {
        leagueService.removeById(id);
    }

    @Override
    public LeagueDTO getLeagueDTO(long id) {
        return leagueService.getLeagueDTOById(id);
    }

    @Override
    public boolean isLeagueExist(long id) {
        return leagueFinder.exist(id);
    }

    @Override
    public List<LeagueDTO> getAllLeagues() {
        return leagueService.getAllLeagues();
    }

}
