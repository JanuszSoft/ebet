package pl.januszsoft.application.UC;

import pl.januszsoft.feature.bet.BetDTO;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UCBet {

    BetDTO makeBet(BetDTO betDTO);
    BetDTO updateBet(BetDTO betDTO);
    void deleteBetById(long id);
    List<BetDTO> listAllBetsByUsername(String username);
    BetDTO getBetById(long id);

}
