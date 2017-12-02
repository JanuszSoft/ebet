package pl.januszsoft.feature.leagueUsersResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.feature.bet.Bet;
import pl.januszsoft.feature.match.Match;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;

@Component
@Transactional
public class UsersResultUpdateService {

    private LeagueUsersResultFinder leagueUsersResultFinder;

    @Autowired
    public UsersResultUpdateService(LeagueUsersResultFinder leagueUsersResultFinder) {
        this.leagueUsersResultFinder = leagueUsersResultFinder;
    }

    public void updateUsersResult(Match match) {
        MatchResult result = match.getResult();
        Set<Bet> bets = match.getBets();
        LeagueUsersResult leagueUsersResult = leagueUsersResultFinder.getLeagueUsersResultFromLeague(match.getLeagueId());
        bets.forEach(bet -> checkBet(result, leagueUsersResult, bet));
    }

    private void checkBet(MatchResult result, LeagueUsersResult leagueUsersResult, Bet bet) {
        MatchResult betResult = bet.getBetResult();
        if (Objects.equals(betResult, result)) {
            leagueUsersResult.addPointToUser(bet.getUsername());
        }
    }

}

