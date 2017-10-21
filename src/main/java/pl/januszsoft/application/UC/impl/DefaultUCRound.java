package pl.januszsoft.application.UC.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCRound;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.RoundEntity;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueCRUDService;
import pl.januszsoft.feature.match.MatchDTO;
import pl.januszsoft.feature.round.Round;
import pl.januszsoft.feature.round.RoundCRUDService;
import pl.januszsoft.feature.round.RoundDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultUCRound extends UC implements UCRound{

    private final RoundCRUDService roundCRUDService;
    private final LeagueCRUDService leagueCRUDService;
    
    
    @Autowired
    public DefaultUCRound(RoundCRUDService roundCRUDService, LeagueCRUDService leagueCRUDService) {
        this.roundCRUDService = roundCRUDService;
        this.leagueCRUDService = leagueCRUDService;
    }


    @Override
    @Transactional
    public RoundDTO addRoundToLeague(RoundDTO roundDTO, long leagueId) {
        League league = leagueCRUDService.getLeagueById(leagueId);
        Round round = league.addNewRoundWithMatches(roundDTO);
        return mapToDTO(round);
    }

    @Override
    public RoundDTO getRoundById(long roundId) {
        Round round = roundCRUDService.getRoundById(roundId);
        return mapToDTO(round);
    }

    @Override
    public RoundDTO getRoundFromLeague(long leagueId, int roundNumber) {
        Round roundFromLeague = leagueCRUDService.getLeagueById(leagueId).getRoundWithNumber(roundNumber);
        return mapToDTO(roundFromLeague);
    }

    @Override
    public List<RoundDTO> getAllRoundsFromLeague(long leagueId) {
        League league = leagueCRUDService.getLeagueById(leagueId);
        return league.getAllRounds().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void removeRoundById(long id) {
        roundCRUDService.removeById(id);
    }

    private RoundDTO mapToDTO(Round round) {
        return mapToDTO(round.attached());
    }

    private RoundDTO mapToDTO(RoundEntity round) {
        ModelMapper mapper = new ModelMapper();
        RoundDTO roundDTO = mapper.map(round, RoundDTO.class);
        Set<MatchEntity> matches = round.getMatches();
        roundDTO.setMatchDTOList(mapMatchList(matches));
        return roundDTO;
    }

    private List<MatchDTO> mapMatchList(Set<MatchEntity> matchEntities) {
        ModelMapper mapper = new ModelMapper();
        return matchEntities.stream().map(e -> mapper.map(e, MatchDTO.class)).collect(Collectors.toList());
    }




}
