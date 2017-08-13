package pl.januszsoft.application.UC.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCRound;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.league.LeagueRepository;
import pl.januszsoft.feature.round.RoundCreator;
import pl.januszsoft.feature.round.RoundInfo;

import java.util.Optional;

@Service
public class DefaultUCRound extends UC implements UCRound{

    private final LeagueRepository leagueRepository;
    private RoundCreator roundCreator;

    @Autowired
    public DefaultUCRound(LeagueRepository leagueRepository, RoundCreator roundCreator) {
        this.leagueRepository = leagueRepository;
        this.roundCreator = roundCreator;
    }


    @Override
    public void addRoundToLeague(RoundInfo roundInfo, long leagueId) {
        Optional<LeagueEntity> league = leagueRepository.findOne(leagueId);
        league.ifPresent(leagueEntity -> {
            RoundEntity entity = roundCreator.createEntity(roundInfo,leagueEntity);
            leagueEntity.addRound(entity);
        });
    }


}
