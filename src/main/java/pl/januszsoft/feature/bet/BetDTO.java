package pl.januszsoft.feature.bet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import pl.januszsoft.entity.MatchResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BetDTO extends ResourceSupport{

    @JsonProperty("id")
    private long betId;
    private long matchId;
    private MatchResult result;
    private String username;

}