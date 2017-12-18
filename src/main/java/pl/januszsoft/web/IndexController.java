package pl.januszsoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public ModelAndView getIndexPage() {
        return new ModelAndView("index");
    }


}
