package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Media;
import fr.unicornteam.uniflix.model.Search;
import fr.unicornteam.uniflix.model.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {

    @PostMapping("/search")
    public String search(@ModelAttribute Search search, Model model) {
        model.addAttribute("searchString", new Search());
        List<Media> results;
        String query;
        try {
            results = Util.research(search.getSearchValue());
            query = "Search results for : ";
        } catch (Exception e) {
            results = Util.allMedia();
            query = "No results for : ";
        }
        query += search.getSearchValue();
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search";
    }
}
