package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Suggestion.MediaSuggestion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscoverController {
    @GetMapping("/discover")
    public String discover(Model model) {
        model.addAttribute("medias", MediaSuggestion.getSuggestion(5));
        return "discover";
    }
}
