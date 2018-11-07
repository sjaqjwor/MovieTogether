package com.movie.movie.domains;

import lombok.Data;

import java.util.Date;

@Data
public class DailyMovie {

    private int rank;
    private int accaudience;
    private int dailyaudience;
    private Date date;
    private int id;
}
