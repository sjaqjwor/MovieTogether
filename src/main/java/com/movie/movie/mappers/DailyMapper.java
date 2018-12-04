package com.movie.movie.mappers;

import com.movie.movie.domains.DailyMovie;
import com.movie.movie.domains.Movie;
import com.movie.movie.domains.dto.TodayMovieDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DailyMapper {
    void insertDailyMovie(DailyMovie dailyMovie);
    List<TodayMovieDto> selectTodayMovieRank(String date);
}
