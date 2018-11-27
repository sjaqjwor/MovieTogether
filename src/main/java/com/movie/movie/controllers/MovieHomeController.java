package com.movie.movie.controllers;


import com.movie.movie.domains.DailyMovie;
import com.movie.movie.services.MovieHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieHomeController {

    @Autowired
    private MovieHomeService movieHomeService;

    @GetMapping(name = "/ex")
    public List<DailyMovie> getMovieRank(){
        long time  = System.currentTimeMillis();
        List<DailyMovie> list =  movieHomeService.getTodayMovieRank();
        long before = System.currentTimeMillis();
        System.out.println(before-time);
        return list;
    }
}
