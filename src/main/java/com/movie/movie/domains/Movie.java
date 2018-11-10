package com.movie.movie.domains;


import com.movie.movie.utils.koflic.MovieInfo;
import com.movie.movie.utils.koflic.ResponseMoviceInfoData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Movie {
    private String mname;
    private String mrelease;
    private String mimageUrl;
    private String mdirector;
    private String mgenre;
    private String mnation;
    private String maudits;
    private int movieCn;

    public Movie buildMovieFromMovieInfo(MovieInfo movieInfo,String title,int movieCn){
        Movie movie = new Movie();
        movie.setMname(title);
        movie.setMrelease(movieInfo.getOpenDt());
        movie.setMimageUrl("not yet");
        movie.setMaudits(movieInfo.getAudits().get(0).get("watchGradeNm"));
        movie.setMdirector(movieInfo.getDirectors().get(0).get("peopleNm"));

        String genre = "";
        for(Map<String,String> mapList : movieInfo.getGenres()){
            genre+=mapList.get("genreNm")+"|";
        }
        movie.setMgenre(genre);
        movie.setMovieCn(movieCn);
        movie.setMnation(movieInfo.getNations().get(0).get("nationNm"));
        return movie;
    }


}
