package com.movie.movie.services;


import com.movie.movie.domains.DailyMovie;
import com.movie.movie.mappers.DailyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MovieHomeService {

    @Autowired
    private DailyMapper dailyMapper;

    @Cacheable(cacheNames = "selectMovieRank" )
    public List<DailyMovie> getTodayMovieRank(){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return dailyMapper.selectTodayMovieRank(today.format(formatter));
    }
}
