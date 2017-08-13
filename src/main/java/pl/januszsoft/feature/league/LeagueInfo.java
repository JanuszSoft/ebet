package pl.januszsoft.feature.league;

import lombok.Data;
import pl.januszsoft.feature.round.RoundInfo;

import java.util.List;

@Data
public class LeagueInfo {
    private long id;
    private String name;
    private List<RoundInfo> roundInfoList;

    public LeagueInfo(long id, String name, List<RoundInfo> roundInfoList) {
        this.id = id;
        this.name = name;
        this.roundInfoList = roundInfoList;
    }
}
