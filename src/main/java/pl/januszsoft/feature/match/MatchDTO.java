package pl.januszsoft.feature.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import pl.januszsoft.entity.MatchResult;

@Data
@NoArgsConstructor
public class MatchDTO extends ResourceSupport {

    @JsonProperty("id")
    private long matchId;
    private String host;
    private String guest;
    private MatchResult matchResult = MatchResult.NOT_SET;


    public MatchDTO(String host, String guest) {
        this.host = host;
        this.guest = guest;
    }
}
