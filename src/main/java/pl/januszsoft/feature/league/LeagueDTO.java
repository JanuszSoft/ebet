package pl.januszsoft.feature.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import pl.januszsoft.feature.round.RoundDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class LeagueDTO extends ResourceSupport{

    @JsonProperty("id")
    private long leagueId;
    private String name;
    private int numberOfRounds;

    public LeagueDTO(long id, String name, int numberOfRounds) {
        this.leagueId = id;
        this.name = name;
        this.numberOfRounds = numberOfRounds;
    }

}
