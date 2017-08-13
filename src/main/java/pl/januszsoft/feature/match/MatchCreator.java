package pl.januszsoft.feature.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.MatchResult;

@Component
public class MatchCreator {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchCreator(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match createMatch(MatchInfo matchInfo){
        MatchEntity entity = new MatchEntity();
        entity.setHost(matchInfo.getHost());
        entity.setGuest(matchInfo.getGuest());
        matchRepository.save(entity);
        return new Match(entity,matchRepository);
    }
    public MatchEntity createMatchEntity(MatchInfo matchInfo){
        MatchEntity entity = new MatchEntity();
        entity.setHost(matchInfo.getHost());
        entity.setGuest(matchInfo.getGuest());
        matchRepository.save(entity);
        return entity;
    }
}
