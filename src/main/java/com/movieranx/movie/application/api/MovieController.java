package com.movieranx.movie.application.api;

import com.movieranx.movie.domain.domain.Movie;
import com.movieranx.movie.domain.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/movie")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService service;

    @CrossOrigin("*")
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable String id){
        log.info("Finding movie by id....");

        Movie movie;

        try{
            movie = service.findMovieById(id);
        } catch (Exception e){
            log.error("Error while finding movie with ID " + id, e.getCause());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(movie == null){
            return new ResponseEntity<>("Could not fund movie", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}