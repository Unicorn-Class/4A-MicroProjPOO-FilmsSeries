package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String settings(Model model) {

        model.addAttribute("searchString", new Search());
        return "settings";
    }
}
