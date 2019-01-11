package fr.unicornteam.uniflix.controller;

import fr.unicornteam.uniflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
    @Autowired
    MovieRepository repo;

    @GetMapping("/movie")
    public String greeting(@RequestParam(name="id", required=false, defaultValue="1") Integer id, Model model) {
        model.addAttribute("movie", repo.findById(id).get());
        return "movie";
    }
}
