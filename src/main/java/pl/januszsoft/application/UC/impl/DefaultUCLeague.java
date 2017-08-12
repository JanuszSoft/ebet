package pl.januszsoft.application.UC.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueCreator;
import pl.januszsoft.feature.league.LeagueInfo;
import pl.januszsoft.feature.league.LeagueRepository;

@Service
public class DefaultUCLeague implements UCLeague{

    private LeagueCreator leagueCreator;
    private LeagueRepository leagueRepository;

    @Autowired
    public void setLeagueCreator(LeagueCreator leagueCreator) {
        this.leagueCreator = leagueCreator;
    }

    @Autowired
    public void setLeagueRepository(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public long createLeague(String name) {
        League league = leagueCreator.createLeague(name);
        return league.getId();
    }

    @Override
    public void removeLeague(String name) {
        if(isLeagueExist(name)) {
            leagueRepository.deleteByName(name);
        }
    }

    @Override
    public LeagueInfo getLeagueInfo(String name) {
        return null;
    }

    @Override
    public boolean isLeagueExist(String name) {
        return leagueRepository.findByName(name).isPresent();
    }

}
