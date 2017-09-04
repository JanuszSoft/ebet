package pl.januszsoft.application.UC;

import pl.januszsoft.feature.round.RoundDTO;

public interface UCRound {

    void addRoundToLeague(RoundDTO roundDTO, long leagueId);

}
