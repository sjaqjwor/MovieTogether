package com.movie.movie.utils.koflic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DailyMovieChart {
    private String boxofficeType;
    private String showRange;
    private List<Map<String,String>> dailyBoxOfficeList;
}
