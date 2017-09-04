package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.LeagueDTO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@RestController
@RequestMapping("/api")
public class ApiLeagueController {

    private final UCLeague ucLeague;

    @Autowired
    public ApiLeagueController(UCLeague ucLeague) {
        this.ucLeague = ucLeague;
    }

    @RequestMapping(value = "/league",method = RequestMethod.POST)
    public LeagueDTO createLeague(@RequestBody HashMap<String,String> payload){
        String name = payload.get("name");
        long leagueId = ucLeague.createLeague(name);
        return createLeagueInfo(name, leagueId);
    }

    private LeagueDTO createLeagueInfo(String name, long leagueId) {
        LeagueDTO leagueDTO = new LeagueDTO(leagueId,name, Collections.emptyList());
        return leagueDTO;
    }

    @RequestMapping("/leagues")
    public List<LeagueDTO> getAllLeague(){
        return ucLeague.getAllLeagues();
    }

    @RequestMapping("/league/{id}")
    public HttpEntity<LeagueDTO> getLeagueById(@PathVariable long id){
        LeagueDTO leagueDTO = ucLeague.getLeagueDTO(id);
        leagueDTO.add(linkTo(methodOn(ApiLeagueController.class).getLeagueById(id)).withSelfRel());
        return new ResponseEntity<LeagueDTO>(leagueDTO, HttpStatus.OK);
    }

}
