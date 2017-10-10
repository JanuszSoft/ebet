package pl.januszsoft.application.UC.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCBet;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.feature.bet.Bet;
import pl.januszsoft.feature.bet.BetCRUDService;
import pl.januszsoft.feature.bet.BetDTO;
import pl.januszsoft.feature.match.Match;
import pl.januszsoft.feature.match.MatchCRUDService;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultUCBet extends UC implements UCBet {

    private final BetCRUDService betCRUDService;
    private final MatchCRUDService matchCRUDService;

    @Autowired
    public DefaultUCBet(BetCRUDService betCRUDService, MatchCRUDService matchCRUDService) {
        this.betCRUDService = betCRUDService;
        this.matchCRUDService = matchCRUDService;
    }

    @Override
    @Transactional
    public BetDTO makeBet(BetDTO betDTO) {
        long matchId = betDTO.getMatchId();
        Match match = matchCRUDService.getMatchById(matchId);
        Bet bet = match.addBet(betDTO);
        return createBetDTO(bet.attached());
    }

    private BetDTO createBetDTO(@NotNull BetEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, BetDTO.class);
    }

    @Override
    @Transactional
    public BetDTO updateBet(BetDTO betDTO) {
        long matchId = betDTO.getMatchId();
        Match match = matchCRUDService.getMatchById(matchId);
        Bet bet = match.updateBet(betDTO);
        return createBetDTO(bet.attached());
    }


    @Override
    public void deleteBetById(long id) {
        betCRUDService.deleteBetById(id);
    }

    @Override
    public List<BetDTO> listAllBetsByUsername(String username) {
        return betCRUDService.getAllBetsByUsername(username);
    }

    @Override
    public BetDTO getBetById(long id) {
        return betCRUDService.getBetById(id);
    }
}
