package pl.januszsoft.feature.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.match.MatchCreator;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoundCreator {

    private final RoundRepository roundRepository;
    private MatchCreator matchCreator;

    @Autowired
    public RoundCreator(RoundRepository roundRepository, MatchCreator matchCreator) {
        this.roundRepository = roundRepository;
        this.matchCreator = matchCreator;
    }

    public Round create(){
        RoundEntity entity = new RoundEntity();
        roundRepository.save(entity);
        return new Round(entity,roundRepository);
    }

    public Round create(RoundInfo roundInfo,LeagueEntity leagueEntity){
        RoundEntity entity = createEntity(roundInfo, leagueEntity);
        return new Round(entity,roundRepository);
    }

    public RoundEntity createEntity(RoundInfo roundInfo, LeagueEntity leagueEntity){
        RoundEntity entity = new RoundEntity();
        entity.setLeagueEntity(leagueEntity);
        List<MatchEntity> matchEntities = roundInfo.getMatchInfoList()
                .stream()
                .map(matchCreator::createMatchEntity)
                .collect(Collectors.toList());
        entity.setMatches(matchEntities);
        matchEntities.forEach(e->e.setRoundEntity(entity));
        roundRepository.save(entity);
        return entity;
    }

}
