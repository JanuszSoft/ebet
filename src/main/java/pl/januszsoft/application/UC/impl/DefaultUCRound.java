package pl.januszsoft.application.UC.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCRound;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueFinder;
import pl.januszsoft.feature.league.LeagueService;
import pl.januszsoft.feature.round.RoundDTO;

import java.util.Optional;

@Service
public class DefaultUCRound extends UC implements UCRound{

    private final LeagueFinder leagueFinder;

    @Autowired
    public DefaultUCRound(LeagueFinder leagueFinder) {
        this.leagueFinder = leagueFinder;
    }


    @Override
    public void addRoundToLeague(RoundDTO roundDTO, long leagueId) {
        leagueFinder.find(leagueId).ifPresent(e->e.addRound(roundDTO));
    }


}
