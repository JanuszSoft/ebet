package pl.januszsoft.application.UC.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCMatch;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueCRUDService;
import pl.januszsoft.feature.match.Match;
import pl.januszsoft.feature.match.MatchDTO;
import pl.januszsoft.feature.match.MatchCRUDService;
import pl.januszsoft.feature.round.Round;
import pl.januszsoft.feature.round.RoundCRUDService;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultUCMatch extends UC implements UCMatch{

    private final MatchCRUDService matchCRUDService;
    private final RoundCRUDService roundCRUDService;
    private final LeagueCRUDService leagueCRUDService;

    @Autowired
    public DefaultUCMatch(MatchCRUDService matchCRUDService, RoundCRUDService roundCRUDService, LeagueCRUDService leagueCRUDService) {
        this.matchCRUDService = matchCRUDService;
        this.roundCRUDService = roundCRUDService;
        this.leagueCRUDService = leagueCRUDService;
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
    public void removeMatchById(long id) {
        matchCRUDService.removeMatchById(id);
    }

    private MatchDTO mapToDTO(MatchEntity matchEntity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(matchEntity,MatchDTO.class);
    }
}
