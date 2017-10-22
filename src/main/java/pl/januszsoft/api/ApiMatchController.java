package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.januszsoft.application.UC.UCMatch;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.feature.match.MatchDTO;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class ApiMatchController {

    private final UCMatch ucMatch;

    @Autowired
    public ApiMatchController(UCMatch ucMatch) {
        this.ucMatch = ucMatch;
    }

    @PostMapping("league/{leagueId}/round/{roundNumber}/match")
    public HttpEntity<MatchDTO> createNewMatch(@PathVariable long leagueId,@PathVariable int roundNumber,@RequestBody MatchDTO matchDTO){
        MatchDTO result = ucMatch.addMatchToRound(leagueId,roundNumber, matchDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/match/{id}")
    public HttpEntity<MatchDTO> getMatchById(@PathVariable long id){
        MatchDTO matchDTO = ucMatch.getMatchById(id);
        matchDTO.add(linkTo(methodOn(ApiMatchController.class).getMatchById(id)).withSelfRel());
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @GetMapping("/league/{leagueId}/round/{roundNumber}/match/{matchNumber}")
    public HttpEntity<MatchDTO> getMatchFromLeague(@PathVariable long leagueId, @PathVariable int roundNumber,@PathVariable int matchNumber){
        MatchDTO matchDTO = ucMatch.getMatchFromLeagueAndRound(leagueId,roundNumber, matchNumber);
        matchDTO.add(linkTo(methodOn(ApiMatchController.class).getMatchById(matchDTO.getMatchId())).withSelfRel());
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @DeleteMapping("/match/{id}")
    public HttpStatus deleteById(@PathVariable long id){
        ucMatch.removeMatchById(id);
        return HttpStatus.NO_CONTENT;
    }

    @PutMapping("/match/{id}")
    public HttpEntity updateMatch(@PathVariable long id, @RequestBody MatchDTO matchDTO) {
        matchDTO.setMatchId(id);
        MatchDTO newMatch = ucMatch.updateMatch(matchDTO);
        newMatch.add(linkTo(methodOn(ApiMatchController.class).getMatchById(matchDTO.getMatchId())).withSelfRel());
        return new ResponseEntity(newMatch, HttpStatus.OK);
    }

    @PostMapping("/match/{id}")
    public HttpEntity setMatchResult(@PathVariable long id, @RequestBody MatchResult matchResult) {
        MatchDTO matchDTO = ucMatch.setMatchResult(id, matchResult);
        matchDTO.add(linkTo(methodOn(ApiMatchController.class).getMatchById(matchDTO.getMatchId())).withSelfRel());
        return new ResponseEntity(matchDTO, HttpStatus.OK);

    }
}
