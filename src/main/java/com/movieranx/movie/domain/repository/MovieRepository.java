package com.movieranx.movie.domain.repository;

import com.movieranx.movie.domain.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

import java.util.ArrayList;

@Repository
@Slf4j
public class MovieRepository {
    private final RestTemplate restTemplate;

    public MovieRepository(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Movie getMovieById(String id) {
        String url = "https://api.themoviedb.org/3/movie/"+id+"?api_key=76f1f87c35d7c82f82a34ca61635e6a4";
        return this.restTemplate.getForObject(url, Movie.class);
    }

    public LinkedHashMap getMovieByName(String name) {
        log.info("get movies by name repository");
        String url = "https://api.themoviedb.org/3/search/movie?api_key=76f1f87c35d7c82f82a34ca61635e6a4&query=" + name;
        try {
            log.info("Requesting for api");
            return this.restTemplate.getForObject(url, LinkedHashMap.class);
        }catch (Exception e){
            log.error("Could not get resposne from API");
            throw e;
        }
    }
}

