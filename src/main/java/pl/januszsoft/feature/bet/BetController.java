package pl.januszsoft.feature.bet;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.januszsoft.entity.BetEntity;

@RestController("/")
public class BetController {

    @Autowired
    private BetRepository betRepository;

    @RequestMapping(value = "a", method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> hello(){

        BetEntity betEntity = new BetEntity();
        ResourceSupport rs = new ResourceSupport();
        rs.add(linkTo(methodOn(BetController.class).hello()).withSelfRel());

        return new ResponseEntity<ResourceSupport>(rs, HttpStatus.OK);
    }


}
