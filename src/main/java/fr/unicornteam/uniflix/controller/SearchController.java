package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.Search;
import fr.unicornteam.uniflix.model.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(@ModelAttribute Search search, Model model) {
        List<Media> results = Util.research(search.getSearchValue());
        model.addAttribute("results", results);
        model.addAttribute("query", search.getSearchValue());
        return "search";
    }
}
