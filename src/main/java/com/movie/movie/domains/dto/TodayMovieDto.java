package com.movie.movie.domains.dto;


import lombok.Data;

@Data
public class TodayMovieDto {
    private String mname;
    private String mrelease;
    private String mimageUrl;
    private String mdirector;
    private String mgenre;
    private String mnation;
    private String maudits;
    private int movieCn;
    private int movierank;
    private int accaudience;
    private int dailyaudience;
}
