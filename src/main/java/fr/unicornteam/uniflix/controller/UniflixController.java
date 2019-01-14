package fr.unicornteam.uniflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UniflixController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
