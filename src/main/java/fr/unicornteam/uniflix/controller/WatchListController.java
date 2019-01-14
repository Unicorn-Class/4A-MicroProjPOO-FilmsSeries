package fr.unicornteam.uniflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WatchListController {


    @GetMapping("/watchlist")
    public String watchlist() {
        return "my-list";
    }

}
