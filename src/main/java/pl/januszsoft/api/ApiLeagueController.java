package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.LeagueInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/league")
public class ApiLeagueController {

    private final UCLeague ucLeague;

    @Autowired
    public ApiLeagueController(UCLeague ucLeague) {
        this.ucLeague = ucLeague;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public LeagueInfo createLeague(@RequestBody HashMap<String,String> payload){
        String name = payload.get("name");
        long leagueId = ucLeague.createLeague(name);
        return createLeagueInfo(name, leagueId);
    }

    private LeagueInfo createLeagueInfo(String name, long leagueId) {
        LeagueInfo leagueInfo = new LeagueInfo(leagueId,name, Collections.emptyList());
        return leagueInfo;
    }

    @RequestMapping("/getall")
    public List<LeagueInfo> getAllLeague(){
        return ucLeague.getAllLeagues();
    }

}
