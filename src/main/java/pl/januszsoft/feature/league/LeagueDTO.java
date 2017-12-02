package pl.januszsoft.feature.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
public class LeagueDTO extends ResourceSupport{

    @JsonProperty("id")
    private long leagueId;
    private String name;

    public LeagueDTO(long id, String name) {
        this.leagueId = id;
        this.name = name;
    }

}
