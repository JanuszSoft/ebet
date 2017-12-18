package pl.januszsoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.januszsoft.feature.league.LeagueDTO;

@RequestMapping("/league")
@Controller
public class LeagueController {

    @GetMapping()
    public ModelAndView getNewLeaguePage() {
        ModelAndView modelAndView = new ModelAndView("newleague");
        modelAndView.addObject("league", new LeagueDTO());
        return modelAndView;
    }

}
