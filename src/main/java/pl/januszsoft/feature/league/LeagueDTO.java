package pl.januszsoft.feature.league;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import pl.januszsoft.feature.round.RoundDTO;

import java.util.List;

@Data
public class LeagueDTO extends ResourceSupport{
    private long leagueId;
    private String name;
    private List<Long> roundsIds;

    public LeagueDTO(long id, String name, List<Long> roundsIds) {
        this.leagueId = id;
        this.name = name;
        this.roundsIds = roundsIds;
    }

}
