package com.movie.movie.domains;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private String name;
    private LocalDateTime release;
    private String imageUrl;
    private String director;
    private String genre;
    private String nation;
    private String audits;
    private String summary;
    private int movieCd;


}
