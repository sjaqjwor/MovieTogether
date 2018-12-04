package com.movie.movie.controllers;


import com.movie.movie.controllers.responses.MovieListResponse;
import com.movie.movie.domains.DailyMovie;
import com.movie.movie.services.MovieHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieHomeController {

    @Autowired
    private MovieHomeService movieHomeService;

    @GetMapping("/movies")
    public ResponseEntity<MovieListResponse> getMovies(){
        MovieListResponse movieListResponse = new MovieListResponse(movieHomeService.getTodayMovieRank());
        return new ResponseEntity<>(movieListResponse,HttpStatus.OK);
    }
}
