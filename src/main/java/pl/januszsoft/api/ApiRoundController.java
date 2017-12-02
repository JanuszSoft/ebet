package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.januszsoft.application.UC.UCRound;
import pl.januszsoft.feature.match.MatchDTO;
import pl.januszsoft.feature.round.RoundDTO;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class ApiRoundController {

    private final UCRound ucRound;

    @Autowired
    public ApiRoundController(UCRound ucRound) {
        this.ucRound = ucRound;
    }

    @PostMapping("/league/{leagueId}/round")
    public HttpEntity<RoundDTO> addRoundToLeague(@RequestBody RoundDTO roundDTO, @PathVariable long leagueId){
        RoundDTO newRound = ucRound.addRoundToLeague(roundDTO, leagueId);
        newRound.add(linkTo(methodOn(ApiRoundController.class).getRoundById(newRound.getRoundId())).withSelfRel());
        return new ResponseEntity<>(newRound, HttpStatus.OK);
    }

    //TODO
    @RequestMapping(value = "/league/{leagueId}/round/{roundNumber}",method = RequestMethod.GET)
    public HttpEntity<RoundDTO> getRoundFromLeague(@PathVariable long leagueId, @PathVariable int roundNumber){
        RoundDTO roundDTO = ucRound.getRoundFromLeague(leagueId, roundNumber);
        roundDTO.getMatchDTOList().forEach(this::addLinkToMatch);
        roundDTO.add(linkTo(methodOn(ApiLeagueController.class).getLeagueById(leagueId)).withRel("league"));
        return createHttpEntity(roundDTO);
    }

    @RequestMapping(value = "/league/{leagueId}/rounds",method = RequestMethod.GET)
    public HttpEntity<List<RoundDTO>> getAllRoundsFromLeague(@PathVariable long leagueId){
        List<RoundDTO> rounds = ucRound.getAllRoundsFromLeague(leagueId);
        rounds.forEach(e->e.add(linkTo(methodOn(ApiRoundController.class).getRoundById(e.getRoundId())).withSelfRel()));
        rounds.forEach(e->e.getMatchDTOList().forEach(this::addLinkToMatch));
        return new ResponseEntity<>(rounds,HttpStatus.OK);
    }

    private void addLinkToMatch(MatchDTO matchDTO) {
        matchDTO.add(linkTo(methodOn(ApiMatchController.class).getMatchById(matchDTO.getMatchId())).withSelfRel());
    }

    @GetMapping("/round/{roundId}")
    public HttpEntity<RoundDTO> getRoundById(@PathVariable long roundId){
        RoundDTO roundDTO = ucRound.getRoundById(roundId);
        roundDTO.add(linkTo(methodOn(ApiLeagueController.class).getLeagueById(roundDTO.getLeagueId())).withRel("league"));
        roundDTO.add(linkTo(methodOn(ApiRoundController.class).getRoundById(roundId)).withSelfRel());
        return createHttpEntity(roundDTO);
    }

    private HttpEntity<RoundDTO> createHttpEntity(RoundDTO roundDTO) {
        long roundId = roundDTO.getRoundId();
        roundDTO.add(linkTo(methodOn(ApiRoundController.class).getRoundById(roundId)).withSelfRel());
        roundDTO.getMatchDTOList().forEach(e-> e.add(linkTo(methodOn(ApiMatchController.class).getMatchById(e.getMatchId())).withSelfRel()));
        return new ResponseEntity<>(roundDTO, HttpStatus.OK);
    }

    @DeleteMapping("/round/{id}")
    public HttpStatus deleteRoundById(@PathVariable long id){
        ucRound.removeRoundById(id);
        return HttpStatus.OK;
    }
}
