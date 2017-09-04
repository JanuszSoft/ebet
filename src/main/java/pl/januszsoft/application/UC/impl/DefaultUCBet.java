package pl.januszsoft.application.UC.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCBet;
import pl.januszsoft.application.utils.UC;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.bet.Bet;
import pl.januszsoft.feature.bet.BetDTO;
import pl.januszsoft.feature.bet.BetService;
import pl.januszsoft.feature.match.Match;
import pl.januszsoft.feature.match.MatchFinder;
import pl.januszsoft.feature.match.MatchService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultUCBet extends UC implements UCBet {

    private final BetService betService;
    private final MatchFinder matchFinder;

    @Autowired
    public DefaultUCBet(BetService betService, MatchFinder matchFinder) {
        this.betService = betService;
        this.matchFinder = matchFinder;
    }

    @Override
    @Transactional
    public long makeBet(BetDTO betDTO) {
       /*
        MatchEntity match = matchService.getMatchById(betDTO.getMatchId());
        BetEntity betEntity = betService.addBet(betDTO, match);
        match.addBet(betDTO);
        matchService.save(match);
        return betEntity.getId();
        */
        long matchId = betDTO.getMatchId();
        Optional<Match> match = matchFinder.find(matchId);
        if(match.isPresent()){
            Match match1 = match.get();
            Bet bet = match1.addBet(betDTO);
            return bet.getId();
        }else{
            throw new ResourceNotFoundException("No Match with id: " + matchId);
        }
    }


    @Override
    public void updateBet(BetDTO betDTO) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteBetById(long id) {
        betService.deleteBetById(id);
    }

    @Override
    public List<BetDTO> listAllBetsByUsername(String username) {
        throw new NotImplementedException();
    }

    @Override
    public BetDTO getBetById(long id) {
        return betService.getBetById(id);
    }
}
