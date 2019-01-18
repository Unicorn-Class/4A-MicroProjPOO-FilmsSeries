package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.model.Movie;
import fr.unicornteam.uniflix.model.Search;
import fr.unicornteam.uniflix.model.Util;
import fr.unicornteam.uniflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MovieController {
    @Autowired
    MovieRepository repo;

    @GetMapping("/movie")
    public String movie(@RequestParam(name="id", required=false, defaultValue="1") Integer id, Model model) {
        model.addAttribute("searchString", new Search());
        Movie movie = null;
        for (Movie m : Util.allMovie()) {
            if (m.getId() == id) movie = m;
        }
        if (movie == null) return "app";
        if (movie.getExtract() == null || movie.getExtract().size() == 0) {
            ArrayList<String> ex = new ArrayList<>();
            ex.add("No extract available for this title...");
            movie.setExtract(ex);
        }
        model.addAttribute("movie", movie);
        return "movie";
    }
}
