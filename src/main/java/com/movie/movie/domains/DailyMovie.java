package com.movie.movie.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class DailyMovie {

    private int movierank;
    private int accaudience;
    private int dailyaudience;
    private LocalDateTime today;
    private int movieCn;


    public DailyMovie(int rank,int accaudience,int dailyaudience,int movieCn){
        this.accaudience=accaudience;
        this.movierank=rank;
        this.dailyaudience=dailyaudience;
        this.movieCn=movieCn;
    }
    public DailyMovie buildDailyMovieFromKoflicObject(Map<String ,String> dailyMap ){
        return new DailyMovie(Integer.parseInt(dailyMap.get("rank")),
                Integer.parseInt(dailyMap.get("audiAcc")),
                Integer.parseInt(dailyMap.get("audiCnt")),
                Integer.parseInt(dailyMap.get("movieCd")));
    }

}
