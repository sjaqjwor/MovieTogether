package com.movie.movie.controllers.responses;

import com.movie.movie.domains.dto.TodayMovieDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieListResponse {
    List<TodayMovieDto> movieList;
}
