package pl.januszsoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.januszsoft.application.UC.UCBet;
import pl.januszsoft.feature.bet.BetDTO;

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

    @PostMapping( "/bet")
    public HttpEntity<BetDTO> createBet(@RequestBody BetDTO betDTO){
        BetDTO createdDTO= ucBet.makeBet(betDTO);
        createdDTO.add(linkTo(methodOn(ApiBetController.class).getBetById(createdDTO.getBetId())).withSelfRel());
        return new ResponseEntity<>(betDTO,HttpStatus.OK);
    }

    @GetMapping("/bet/{id}")
    public HttpEntity<BetDTO> getBetById(@PathVariable long id){
        BetDTO betDTO = ucBet.getBetById(id);
        betDTO.add(linkTo(methodOn(ApiBetController.class).getBetById(betDTO.getBetId())).withSelfRel());
        return new ResponseEntity<>(betDTO, HttpStatus.OK);
    }

    @PutMapping("/bet/{id}")
    public HttpEntity<BetDTO> updateBet(@PathVariable long id,@RequestBody BetDTO betDTO){
        betDTO.setBetId(id);
        BetDTO updatedBet = ucBet.updateBet(betDTO);
        updatedBet.add(linkTo(methodOn(ApiBetController.class).getBetById(betDTO.getBetId())).withSelfRel());
        return new ResponseEntity<>(updatedBet, HttpStatus.OK);
    }

    @DeleteMapping("/bet/{id}")
    public HttpStatus deleteById(@PathVariable long id){
        ucBet.deleteBetById(id);
        return HttpStatus.NO_CONTENT;
    }

}
