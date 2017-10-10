package pl.januszsoft.feature.round;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import pl.januszsoft.feature.match.MatchDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RoundDTO extends ResourceSupport {

    @JsonProperty("id")
    private long roundId;

    private long leagueId;

    @JsonProperty("matchList")
    private List<MatchDTO> matchDTOList  = new ArrayList<>();

    public RoundDTO(List<MatchDTO> matchDTOList) {
        this.matchDTOList = matchDTOList;
    }

}
