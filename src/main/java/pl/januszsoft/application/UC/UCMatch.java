package pl.januszsoft.application.UC;

import pl.januszsoft.feature.match.MatchDTO;

import javax.transaction.Transactional;

@Transactional
public interface UCMatch  {

    MatchDTO getMatchById(long id);
    MatchDTO getMatchFromLeagueAndRound(long leagueId,int roundNumber, int matchNumber);
    MatchDTO addMatchToRound(long leagueId,int roundNumber,MatchDTO match);
    void removeMatchById(long id);

}
