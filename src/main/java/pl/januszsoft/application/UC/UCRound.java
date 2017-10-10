package pl.januszsoft.application.UC;

import pl.januszsoft.feature.round.RoundDTO;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UCRound {

    RoundDTO addRoundToLeague(RoundDTO roundDTO, long leagueId);
    RoundDTO getRoundById(long roundId);
    RoundDTO getRoundFromLeague(long leagueId, int roundNumber);
    List<RoundDTO> getAllRoundsFromLeague(long leagueId);
    void removeRoundById(long id);
}
