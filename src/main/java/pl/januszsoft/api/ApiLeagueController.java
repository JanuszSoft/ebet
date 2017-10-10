package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.feature.league.League;
import pl.januszsoft.feature.league.LeagueDTO;

import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/api")
public class ApiLeagueController {

    private final UCLeague ucLeague;

    @Autowired
    public ApiLeagueController(UCLeague ucLeague) {
        this.ucLeague = ucLeague;
    }

    @PostMapping("/league")
    public HttpEntity<LeagueDTO> createLeague(@RequestBody LeagueDTO leagueDTO){
        String name = leagueDTO.getName();
        long leagueId = ucLeague.createLeague(name);
        LeagueDTO newLeague = createLeagueDTOWithNoRounds(name,leagueId);
        return new ResponseEntity(newLeague,HttpStatus.OK);
    }

    private LeagueDTO createLeagueDTOWithNoRounds(String name, long leagueId) {
        LeagueDTO leagueDTO = new LeagueDTO(leagueId,name, 0);
        leagueDTO.add(linkTo(methodOn(ApiLeagueController.class).getLeagueById(leagueId)).withSelfRel());
        leagueDTO.add(linkTo(methodOn(ApiRoundController.class).getAllRoundsFromLeague(leagueId)).withRel("All rounds"));
        return leagueDTO;
    }

    @GetMapping("/leagues")
    public HttpEntity<List<LeagueDTO>> getAllLeague(){
        List<LeagueDTO> allLeagues = ucLeague.getAllLeagues();
        allLeagues.forEach(e->e.add(linkTo(methodOn(ApiLeagueController.class).getLeagueById(e.getLeagueId())).withSelfRel()));
        return new ResponseEntity<>(allLeagues,HttpStatus.OK);
    }

    @GetMapping("/league/{id}")
    public HttpEntity<LeagueDTO> getLeagueById(@PathVariable long id){
        LeagueDTO leagueDTO = ucLeague.getLeagueDTO(id);
        leagueDTO.add(linkTo(methodOn(ApiLeagueController.class).getLeagueById(id)).withSelfRel());
        Link link = linkTo(methodOn(ApiRoundController.class).getAllRoundsFromLeague(id)).withRel("All rounds");
        leagueDTO.add(link);
        return new ResponseEntity<>(leagueDTO, HttpStatus.OK);
    }

    @DeleteMapping("league/{id}")
    public HttpStatus deleteLeagueById(@PathVariable long id){
        ucLeague.removeLeague(id);
        return HttpStatus.NO_CONTENT;
    }

}
