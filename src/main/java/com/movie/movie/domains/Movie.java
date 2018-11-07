package com.movie.movie.domains;


import lombok.Data;

import java.util.Date;

@Data
public class Movie {
    private String name;
    private Date release;
    private String imageUrl;
    private String director;
    private String genre;
    private String nation;
    private String audits;
    private String summary;
    private int movieCd;
}
