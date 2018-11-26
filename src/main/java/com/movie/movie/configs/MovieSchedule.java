package com.movie.movie.configs;

import com.movie.movie.domains.DailyMovie;
import com.movie.movie.domains.Movie;
import com.movie.movie.mappers.DailyMapper;
import com.movie.movie.mappers.MovieMapper;
import com.movie.movie.utils.koflic.ResponseDailyMoviceChartData;
import com.movie.movie.utils.koflic.ResponseMoviceInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class MovieSchedule {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private DailyMapper dailyMapper;

    @Value("${kofic.api.key}")
    private String koficKey;

    @Value("${kofic.daily.url}")
    private String koficDailyUrl;

    @Value("${kofic.movieinfo.url}")
    private String koficMovieinfoUrl;

    @Value("${naver.client.id}")
    private String naverClientid;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url}")
    private String naverUrl;


    @Scheduled(cron = " 0 17 15 * * *")
    @Transactional
    public void insertMovieInformation() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        LocalDateTime now = LocalDateTime.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(koficDailyUrl).queryParam("key", koficKey)
                .queryParam("targetDt", now.format(formatter));


        ResponseEntity<ResponseDailyMoviceChartData> responseKoflicDataResponseEntity =
                restTemplate.getForEntity(builder.build().toUri(), ResponseDailyMoviceChartData.class);


        if (responseKoflicDataResponseEntity.getStatusCode().is2xxSuccessful()) {
            List<Map<String, String>> getDailyBoxOfficeList = responseKoflicDataResponseEntity.getBody().getBoxOfficeResult().getDailyBoxOfficeList();

            headers.add("X-Naver-Client-Id", naverClientid);
            headers.add("X-Naver-Client-Secret", naverClientSecret);

            for (Map<String, String> dailyMap : getDailyBoxOfficeList) {
                DailyMovie dailyMovie = new DailyMovie().buildDailyMovieFromKoflicObject(dailyMap);
                dailyMapper.insertDailyMovie(dailyMovie);

                if (movieMapper.selectByMovieCd(Integer.parseInt(dailyMap.get("movieCd"))) == null) {
                    builder = UriComponentsBuilder.fromHttpUrl(koficMovieinfoUrl).queryParam("key", koficKey).queryParam("movieCd", dailyMap.get("movieCd"));
                    ResponseEntity<ResponseMoviceInfoData> movieinfo = restTemplate.getForEntity(builder.build().toUri(), ResponseMoviceInfoData.class);
                    Movie movie = new Movie().buildMovieFromMovieInfo(movieinfo.getBody().getMovieInfoResult().getMovieInfo(), dailyMap.get("movieNm"), Integer.parseInt(dailyMap.get("movieCd")));
                    movieMapper.insertMovie(movie);
                }

            }
        }

    }

}
