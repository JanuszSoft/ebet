package pl.januszsoft.feature.match;

import lombok.Data;

@Data
public class MatchDTO {
    long id;
    private String host;
    private String guest;

    public MatchDTO(){

    }

    public MatchDTO(long id, String host, String guest) {
        this.id = id;
        this.host = host;
        this.guest = guest;
    }

    public MatchDTO(String host, String guest) {
        this.host = host;
        this.guest = guest;
    }

}
