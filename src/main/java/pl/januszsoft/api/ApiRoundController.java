package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.januszsoft.application.UC.UCRound;
import pl.januszsoft.feature.match.MatchDTO;
import pl.januszsoft.feature.round.RoundDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiRoundController {


    private final UCRound ucRound;

    @Autowired
    public ApiRoundController(UCRound ucRound) {
        this.ucRound = ucRound;
    }

    @RequestMapping(value = "/round",method = RequestMethod.POST)
    public void addRoundToLeague(@RequestBody Map<String,Object> payload){
        long leagueId = Long.valueOf((String)payload.get("leagueId"));
        ArrayList<LinkedHashMap> matches = (ArrayList<LinkedHashMap>) payload.get("matches");
        List<MatchDTO> matchDTOList = matches.stream().map(this::createMatchInfo).collect(Collectors.toList());
        RoundDTO info = new RoundDTO(matchDTOList);
        ucRound.addRoundToLeague(info,leagueId);
    }

    private MatchDTO createMatchInfo(LinkedHashMap match){
        String host = (String)match.get("host");
        String guest = (String)match.get("guest");
        return new MatchDTO(host,guest);
    }
}
