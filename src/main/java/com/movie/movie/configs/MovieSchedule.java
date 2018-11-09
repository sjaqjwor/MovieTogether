package com.movie.movie.configs;

import com.movie.movie.domains.DailyMovie;
import com.movie.movie.mappers.DailyMapper;
import com.movie.movie.utils.restobject.ResponseKoflicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class MovieSchedule {



    @Autowired
    private DailyMapper dailyMapper;

    @Value("${kofic.api.key}")
    private String koficKey;

    @Value("${kofic.url}")
    private String koficUrl;

    @Value("${naver.client.id}")
    private String naverClientid;

    @Value("${naver.client.secret}")
    private String naverClientSecret;



    @Scheduled(cron = " 0 0 1 * * *")
    public void inserMovieInformation(){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        LocalDateTime now = LocalDateTime.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(koficUrl).queryParam("key",koficKey)
                .queryParam("targetDt",now.format(formatter));

        ResponseEntity<ResponseKoflicData> responseKoflicDataResponseEntity =
                restTemplate.getForEntity(builder.build().toUri(),ResponseKoflicData.class);

        if(responseKoflicDataResponseEntity.getStatusCode().is2xxSuccessful()){
            List<Map<String,String>> getDailyBoxOfficeList = responseKoflicDataResponseEntity.getBody().getBoxOfficeResult().getDailyBoxOfficeList();
            for(Map<String,String> dailyMap : getDailyBoxOfficeList){
                DailyMovie dailyMovie = new DailyMovie().buildDailyMovieFromKoflicObject(dailyMap);
                dailyMapper.insetDailyMovie(dailyMovie);
            }
        }

    }

}
