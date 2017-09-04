package pl.januszsoft.feature.bet;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import pl.januszsoft.entity.MatchResult;

@Data
public class BetDTO extends ResourceSupport{

    private long betId;
    private long matchId;
    private MatchResult result;
    private String username;

    public BetDTO() {
    }

    public BetDTO(long matchId, MatchResult result, String username) {
        this.matchId = matchId;
        this.result = result;
        this.username = username;
    }

    public BetDTO(long betId, long matchId, MatchResult result, String username) {
        this.betId = betId;
        this.matchId = matchId;
        this.result = result;
        this.username = username;
    }
}