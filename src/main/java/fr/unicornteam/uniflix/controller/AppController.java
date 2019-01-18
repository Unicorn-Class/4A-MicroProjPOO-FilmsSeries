package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping("/app")
    public String app(Model model) {
        return index(model);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newMovies", Util.allMovie());
        model.addAttribute("newSeries", Util.allMovie());
        model.addAttribute("recommended", Util.allMovie());
        System.out.println("Util.allMedia().size() = " + Util.allMedia().size());
        return "app";
    }

    @GetMapping("/media")
    public String redirect(@RequestParam(name="id", required = true, defaultValue="1") Integer id,
                           @RequestParam(name="class", required=true, defaultValue = "fr.unicornteam.uniflix.model.Movie") String className,
                           Model model) {
        if (className == "fr.unicornteam.uniflix.model.Movie") {
            return (new MovieController()).movie(id, model);
        } else {
            return (new MovieController()).movie(id, model);
        }
    }

}
