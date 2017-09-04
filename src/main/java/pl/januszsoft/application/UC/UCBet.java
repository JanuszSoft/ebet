package pl.januszsoft.application.UC;

import pl.januszsoft.feature.bet.BetDTO;

import java.util.List;

public interface UCBet {

    long makeBet(BetDTO betDTO);
    void updateBet(BetDTO betDTO);
    void deleteBetById(long id);
    List<BetDTO> listAllBetsByUsername(String username);
    BetDTO getBetById(long id);

}
