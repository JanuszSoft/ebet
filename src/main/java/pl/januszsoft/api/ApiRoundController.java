package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.januszsoft.application.UC.UCRound;
import pl.januszsoft.feature.match.MatchInfo;
import pl.januszsoft.feature.round.RoundInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/round")
public class ApiRoundController {


    private final UCRound ucRound;

    @Autowired
    public ApiRoundController(UCRound ucRound) {
        this.ucRound = ucRound;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addRoundToLeague(@RequestBody Map<String,Object> payload){
        long leagueId = Long.valueOf((String)payload.get("leagueId"));
        ArrayList<LinkedHashMap> matches = (ArrayList<LinkedHashMap>) payload.get("matches");
        List<MatchInfo> matchInfoList = matches.stream().map(this::createMatchInfo).collect(Collectors.toList());
        RoundInfo info = new RoundInfo(matchInfoList);
        ucRound.addRoundToLeague(info,leagueId);
    }

    private MatchInfo createMatchInfo(LinkedHashMap match){
        String host = (String)match.get("host");
        String guest = (String)match.get("guest");
        return new MatchInfo(host,guest);
    }
}
