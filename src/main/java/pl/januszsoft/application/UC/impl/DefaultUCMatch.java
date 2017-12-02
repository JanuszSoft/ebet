package pl.januszsoft.application.UC.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCMatch;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueCRUDService;
import pl.januszsoft.feature.leagueUsersResults.UsersResultUpdateService;
import pl.januszsoft.feature.match.Match;
import pl.januszsoft.feature.match.MatchCRUDService;
import pl.januszsoft.feature.match.MatchDTO;
import pl.januszsoft.feature.round.Round;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultUCMatch extends UC implements UCMatch{

    private final MatchCRUDService matchCRUDService;
    private final LeagueCRUDService leagueCRUDService;
    private final UsersResultUpdateService usersResultUpdateService;

    @Autowired
    public DefaultUCMatch(MatchCRUDService matchCRUDService, LeagueCRUDService leagueCRUDService, UsersResultUpdateService usersResultUpdateService) {
        this.matchCRUDService = matchCRUDService;
        this.leagueCRUDService = leagueCRUDService;
        this.usersResultUpdateService = usersResultUpdateService;
    }


    @Override
    public MatchDTO getMatchById(long id) {
        Match match = matchCRUDService.getMatchById(id);
        return mapToDTO(match.attached());
    }

    @Override
    public MatchDTO getMatchFromLeagueAndRound(long leagueId,int roundNumber, int matchNumber) {
        League league = leagueCRUDService.getLeagueById(leagueId);
        Round round = league.getRoundWithNumber(roundNumber);
        Match match = round.getMatchWithNumber(matchNumber);
        return mapToDTO(match.attached());
    }

    @Override
    public MatchDTO addMatchToRound(long leagueId, int roundNumber, MatchDTO match) {
        League league = leagueCRUDService.getLeagueById(leagueId);
        Round round = league.getRoundWithNumber(roundNumber);
        Match createdMatch = round.addMatch(match);
        return mapToDTO(createdMatch.attached());
    }

    @Override
    public MatchDTO updateMatch(MatchDTO matchDTO) {
        long id = matchDTO.getMatchId();
        Match match = matchCRUDService.getMatchById(id);
        match.updateHost(matchDTO.getHost());
        match.updateGuest(matchDTO.getGuest());
        return mapToDTO(match);
    }

    @Override
    public void removeMatchById(long id) {
        matchCRUDService.removeMatchById(id);
    }

    @Override
    public MatchDTO setMatchResult(long id, MatchResult matchResult) {
        Match match = matchCRUDService.getMatchById(id);
        match.setResult(matchResult);
        usersResultUpdateService.updateUsersResult(match);
        return mapToDTO(match);
    }

    private MatchDTO mapToDTO(MatchEntity matchEntity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(matchEntity,MatchDTO.class);
    }

    private MatchDTO mapToDTO(Match match) {
        return mapToDTO(match.attached());
    }
}
