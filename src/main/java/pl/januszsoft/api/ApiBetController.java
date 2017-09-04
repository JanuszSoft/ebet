package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.januszsoft.application.UC.UCBet;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.entity.MatchResult;
import pl.januszsoft.feature.bet.BetDTO;
import pl.januszsoft.feature.league.LeagueDTO;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/api")
public class ApiBetController {

    private UCBet ucBet;

    @Autowired
    public ApiBetController(UCBet ucBet) {
        this.ucBet = ucBet;
    }

    @RequestMapping(value = "/bet",method = RequestMethod.POST)
    public BetDTO createLeague(@RequestBody BetDTO betDTO){
        long betId = ucBet.makeBet(betDTO);
        betDTO.setBetId(betId);
        return betDTO;
    }

    @RequestMapping(value = "/bet/{id}",method = RequestMethod.GET)
    public BetDTO getBetById(@PathVariable long id){
        return ucBet.getBetById(id);
    }

    @RequestMapping(value = "/bet",method = RequestMethod.GET)
    public BetDTO getEmptyDTO(){
        return new BetDTO(-1,-1,MatchResult.DRAW,"default");
    }

}
