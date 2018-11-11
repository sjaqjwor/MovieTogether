package com.movie.movie.mappers;

import com.movie.movie.domains.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieMapper {
    void insertMovie(Movie movie);
    Movie selectByMovieCd(Integer movieCd);
}
