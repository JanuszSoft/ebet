package pl.januszsoft.feature.match;

import lombok.Data;

@Data
public class MatchInfo {
    long id;
    private String host;
    private String guest;

    public MatchInfo(long id, String host, String guest) {
        this.id = id;
        this.host = host;
        this.guest = guest;
    }

    public MatchInfo(String host, String guest) {
        this.host = host;
        this.guest = guest;
    }

}
