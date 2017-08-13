package pl.januszsoft.application.UC.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueCreator;
import pl.januszsoft.feature.league.LeagueInfo;
import pl.januszsoft.feature.league.LeagueRepository;
import pl.januszsoft.feature.match.MatchInfo;
import pl.januszsoft.feature.round.RoundInfo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultUCLeague implements UCLeague{

    private final LeagueCreator leagueCreator;
    private final LeagueRepository leagueRepository;

    @Autowired
    public DefaultUCLeague(LeagueCreator leagueCreator, LeagueRepository leagueRepository) {
        this.leagueCreator = leagueCreator;
        this.leagueRepository = leagueRepository;
    }


    @Override
    public long createLeague(String name) {
        League league = leagueCreator.createLeague(name);
        return league.getId();
    }

    @Override
    public void removeLeague(long id) {
        leagueRepository.delete(id); //TODO nie usuwac
    }

    @Override
    public LeagueInfo getLeagueInfo(long id) {
        return null;
    }

    @Override
    public boolean isLeagueExist(long id) {
        return leagueRepository.findOne(id)!=null;
    }

    @Override
    public List<LeagueInfo> getAllLeagues() {
        return leagueRepository.findAll()
                .stream()
                .map(e->new LeagueInfo(e.getId(),e.getName(),e.getRoundEntities()
                        .stream()
                        .map(x->new RoundInfo(x.getId(),-1,x.getMatches() //TODO nr kolejki
                                .stream()
                                .map(m->new MatchInfo(m.getId(),m.getHost(),m.getGuest()))
                                .collect(Collectors.toList())))
                        .collect(Collectors.toList())))
        .collect(Collectors.toList());

    }

}
