package pl.januszsoft.feature.leagueUsersResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.januszsoft.application.utils.Finder;
import pl.januszsoft.entity.LeagueUsersResultsEntity;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueFinder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class LeagueUsersResultFinder implements Finder<LeagueUsersResult, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private LeagueFinder leagueFinder;

    @Autowired
    public LeagueUsersResultFinder(LeagueFinder leagueFinder) {
        this.leagueFinder = leagueFinder;
    }

    @Override
    public Optional<LeagueUsersResult> find(Long id) {
        LeagueUsersResultsEntity leagueUsersResultsEntity = entityManager.find(LeagueUsersResultsEntity.class, id);
        if (leagueUsersResultsEntity != null && leagueUsersResultsEntity.isActive()) {
            return Optional.of(new LeagueUsersResult(leagueUsersResultsEntity, entityManager));
        }
        return Optional.empty();
    }

    @Override
    public boolean exist(Long id) {
        return find(id).isPresent();
    }

    public LeagueUsersResult getLeagueUsersResultFromLeague(long leagueId) {
        Optional<League> league = leagueFinder.find(leagueId);
        return league.orElseThrow(() -> new ResourceNotFoundException("No league with id: " + leagueId)).getLeagueUsersResult();
    }
}
