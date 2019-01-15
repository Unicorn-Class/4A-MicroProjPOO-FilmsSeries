package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.Util;
import fr.unicornteam.uniflix.repositories.MediaRepository;
import fr.unicornteam.uniflix.repositories.MovieRepository;
import fr.unicornteam.uniflix.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newMovies", Util.allMovie());
        model.addAttribute("newSeries", Util.allMedia());
        model.addAttribute("recommended", Util.allMedia());
        System.out.println("Util.allMedia().size() = " + Util.allMedia().size());
        for (Media m : Util.allMedia()) {
            System.out.println("m.getClass().getTypeName() = " + m.getClass().getTypeName());
        }
        return "app";
    }

    @GetMapping("/media")
    public String redirect(@RequestParam(name="id", required = true, defaultValue="1") Integer id,
                           @RequestParam(name="class", required=true, defaultValue = "fr.unicornteam.uniflix.model.Movie") String className,
                           Model model) {
        if (className.split(".")[className.split(".").length] == "Movie") {
            return (new MovieController()).movie(id, model);
        } else {
            return (new MovieController()).movie(id, model);
        }
    }

}
