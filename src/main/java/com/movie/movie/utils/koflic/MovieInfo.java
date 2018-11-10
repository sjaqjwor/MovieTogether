package com.movie.movie.utils.koflic;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MovieInfo {
    List<Map<String,String>> nations;
    List<Map<String,String>>directors;
    List<Map<String,String>> actors;
    private String openDt;
    List<Map<String,String>> audits;
    List<Map<String,String>> genres;
}
