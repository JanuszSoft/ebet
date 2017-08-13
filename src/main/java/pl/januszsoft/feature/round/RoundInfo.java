package pl.januszsoft.feature.round;

import lombok.Data;
import pl.januszsoft.feature.match.MatchInfo;

import java.util.List;

@Data
public class RoundInfo {
    private long id;
    private int number;
    private List<MatchInfo> matchInfoList;

    public RoundInfo(List<MatchInfo> matchInfoList) {
        this.matchInfoList = matchInfoList;
    }

    public RoundInfo(int number, List<MatchInfo> matchInfoList) {
        this.number = number;
        this.matchInfoList = matchInfoList;
    }

    public RoundInfo(long id, int number, List<MatchInfo> matchInfoList) {
        this.id = id;
        this.number = number;
        this.matchInfoList = matchInfoList;
    }
}
