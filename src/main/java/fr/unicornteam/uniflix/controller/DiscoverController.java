package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Suggestion.MediaSuggestion;
import fr.unicornteam.uniflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscoverController {

    @Autowired
    MovieRepository repo;

    @GetMapping("/discover")
    public String discover(Model model) {
        model.addAttribute("medias", repo.findAll());
        return "discover";
    }
}
