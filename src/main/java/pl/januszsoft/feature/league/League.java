package pl.januszsoft.feature.league;

import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.businessObjects.BusinessObject;
import pl.januszsoft.feature.leagueUsersResults.LeagueUsersResult;
import pl.januszsoft.feature.match.MatchDTO;
import pl.januszsoft.feature.round.Round;
import pl.januszsoft.feature.round.RoundDTO;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
public class League extends BusinessObject<LeagueEntity> {


    public League(LeagueEntity entity, EntityManager entityManager) {
        super(entity, LeagueEntity.class, entityManager);
    }

    public int getRoundsNumber(){
        return attached().getRoundEntities().size();
    }

    public Round getRoundWithNumber(final int roundNumber){
        Set<RoundEntity> entitiesSet = attached().getRoundEntities();
        List<RoundEntity> roundEntities = new ArrayList<>(entitiesSet);
        if(roundEntities.size()<=roundNumber){
            throw new ResourceNotFoundException("No round with number "+roundNumber);
        }
        RoundEntity roundEntity = roundEntities.get(convertHumanNumberToITNumber(roundNumber));
        return new Round(roundEntity,entityManager);
    }

    public Round addNewRoundWithMatches(RoundDTO roundDTO){
        RoundEntity roundEntity = createRoundEntity();
        entityManager.persist(roundEntity);
        Round newRound = new Round(roundEntity, entityManager);
        List<MatchDTO> matchDTOList = roundDTO.getMatchDTOList();
        matchDTOList.forEach(newRound::addMatch);
        return newRound;
    }

    private RoundEntity createRoundEntity() {
        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setLeagueEntity(attached());
        return roundEntity;
    }

    public List<Round> getAllRounds() {
        return attached().getRoundEntities().stream().map(e->new Round(e,entityManager)).collect(Collectors.toList());
    }

    public LeagueUsersResult getLeagueUsersResult() {
        return new LeagueUsersResult(attached().getLeagueUsersResultsEntity(), entityManager);
    }

    @Override
    public void delete(){
        getAllRounds().forEach(Round::delete);
        removeAllRounds();
        super.delete();
    }

    private void removeAllRounds() {
        getAllRounds().clear();
    }

    public void updateName(String name) {
        attached().setName(name);
    }
}
