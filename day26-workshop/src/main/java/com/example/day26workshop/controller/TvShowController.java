package com.example.day26workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.day26workshop.models.TvShow;
import com.example.day26workshop.services.TvShowService;


@Controller
public class TvShowController {
    
    @Autowired
    private TvShowService tvShowSvc;
    // GET tvshow?lang=English

    @GetMapping(path="/tvshow")
    public String getTvShow(@RequestParam(defaultValue = "English") String lang, Model model) {

        List<TvShow> results = tvShowSvc.findAllTvShowsByLanguage(lang);
        model.addAttribute("tvshows", results);
        model.addAttribute("language", lang);
        return "tvshows";
    }

    @GetMapping(path="/")
    public String getAllType(Model model) {

        List<String> typeResults = tvShowSvc.findAllTypes();
        model.addAttribute("typeResults", typeResults);
        return "types";
    }

    @GetMapping("/{type}")
    public String getShowsFromType(@PathVariable String type, Model model) {
        List<TvShow> results = tvShowSvc.findAllTvShowsByType(type);
        model.addAttribute("results", results);
        model.addAttribute("type", type);
        return "showsByType";
    }
}
