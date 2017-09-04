package pl.januszsoft.feature.bet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.entity.entity.AbstractEntity;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class BetService {

    private final BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public void deleteBetById(long id) {
        betRepository.findOne(id).ifPresent(AbstractEntity::deactivate);
    }

    public BetDTO getBetById(long id) {
        final Optional<BetEntity> entity = betRepository.findOne(id);
        return entity.map(this::createBetDTO).orElseThrow(() -> new ResourceNotFoundException("No Bet with id: " + id));
    }

    private BetDTO createBetDTO(@NotNull BetEntity entity) {
        long id = entity.getId();
        long matchId = entity.getMatchId();
        MatchResult result = entity.getBetResult();
        String username = entity.getUsername();
        return new BetDTO(id, matchId, result, username);
    }

    public BetEntity addBet(BetDTO betDTO, MatchEntity match) {
        BetEntity entity = new BetEntity();
        entity.setBetResult(betDTO.getResult());
        entity.setUsername(betDTO.getUsername());
        entity.setMatch(match);
        match.addBet(entity);
        betRepository.save(entity);
        return entity;
    }
}