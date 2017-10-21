package pl.januszsoft.feature.round;

import org.springframework.beans.factory.annotation.Configurable;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.businessObjects.BusinessObject;
import pl.januszsoft.feature.match.Match;
import pl.januszsoft.feature.match.MatchDTO;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configurable
public class Round extends BusinessObject<RoundEntity> {

    public Round(RoundEntity entity,EntityManager entityManager) {
        super(entity, RoundEntity.class,entityManager);
    }

    @Transactional
    public Match addMatch(MatchDTO matchDTO){
        MatchEntity matchEntity = createMatchEntity(matchDTO);
        entityManager.persist(matchEntity);
        attached().addMatch(matchEntity);
        return new Match(matchEntity,entityManager);
    }
    

    private MatchEntity createMatchEntity(MatchDTO matchDTO) {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setRoundEntity(attached());
        matchEntity.setHost(matchDTO.getHost());
        matchEntity.setGuest(matchDTO.getGuest());
        return matchEntity;
    }

    @Transactional
    public Match getMatchWithNumber(long matchNumber) {
        Set<MatchEntity> matches = attached().getMatches();
        List<MatchEntity> matchEntities = new ArrayList<>(matches);
        if(matchEntities.size()>matchNumber){
            throw new ResourceNotFoundException("No match with number "+matchNumber);
        }
        MatchEntity match = matchEntities.get(convertHumanNumberToITNumber(matchNumber));
        return new Match(match,entityManager);
    }

    @Transactional
    public List<Match> getAllMatches(){
        return attached().getMatches().stream().map(e->new Match(e,entityManager)).collect(Collectors.toList());
    }

    @Override
    public void delete(){
        getAllMatches().forEach(Match::delete);
        super.delete();
    }

}
