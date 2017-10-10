package pl.januszsoft.feature.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@NoArgsConstructor
public class MatchDTO extends ResourceSupport {

    @JsonProperty("id")
    private long matchId;

    private String host;

    private String guest;

    public MatchDTO(String host, String guest) {
        this.host = host;
        this.guest = guest;
    }
}
