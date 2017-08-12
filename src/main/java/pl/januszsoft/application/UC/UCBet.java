package pl.januszsoft.application.UC;

import pl.januszsoft.entity.BetEntity;

import java.util.List;

public interface UCBet {

    void makeBet(BetEntity betEntity);
    void updateBet(BetEntity betEntity);
    void deleteBetById(long id);
    List<BetEntity> listAllBetsByUsername(String username);
    BetEntity getBetById(long id);

}
