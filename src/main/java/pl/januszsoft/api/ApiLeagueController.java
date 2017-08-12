package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.LeagueInfo;

import java.util.HashMap;

@RestController
@RequestMapping("/api/league")
public class ApiLeagueController {

    @Autowired
    private UCLeague ucLeague;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public LeagueInfo createLeague(@RequestBody HashMap<String,String> payload){
        String name = payload.get("name");
        long leagueId = ucLeague.createLeague(name);
        return createLeagueInfo(name, leagueId);
    }

    private LeagueInfo createLeagueInfo(String name, long leagueId) {
        LeagueInfo leagueInfo = new LeagueInfo();
        leagueInfo.setName(name);
        leagueInfo.setId(leagueId);
        return leagueInfo;
    }

}
