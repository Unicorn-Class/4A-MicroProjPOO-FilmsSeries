package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.repositories.MediaRepository;
import fr.unicornteam.uniflix.repositories.MovieRepository;
import fr.unicornteam.uniflix.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    MovieRepository ttot;


    /*
    @Autowired
    SerieRepository serieRepo;
    @Autowired
    MediaRepository mediaRepo;
    */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newMovies", ttot.findAll());
        /*
        model.addAttribute("newSeries", serieRepo.findAll());
        model.addAttribute("recommended", mediaRepo.findAll());
        */
        return "app";
    }

}
