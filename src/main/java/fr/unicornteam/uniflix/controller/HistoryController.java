package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Search;
import fr.unicornteam.uniflix.model.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("searchString", new Search());
        model.addAttribute("medias", Util.allMedia());
        return "history";
    }
}
