package ro.ubb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.ubb.model.Movie;
import ro.ubb.model.dto.GenreDTO;
import ro.ubb.model.exceptions.ValidationException;
import org.springframework.web.bind.annotation.*;
import ro.ubb.service.MovieService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> findAllMoviesForAYear(@PathVariable int pageNumber){
        try {
            return new ResponseEntity<>(movieService.findAllMovieForTheCurrentYear(pageNumber), HttpStatus.OK);
        } catch (JsonProcessingException | ValidationException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public ResponseEntity<List<GenreDTO>> findAllGenres(){
        try {
            return new ResponseEntity<>(movieService.findAllGenresAndCountMovies(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/genre/{id}/page/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> findAllMoviesWithASpecificGenre(@PathVariable int pageNumber, @PathVariable int id) {
        try {
            return new ResponseEntity<>(movieService.findAllMoviesForAGenre(id, pageNumber), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Movie> findMovieById(@PathVariable int movieId){
        try {
            return new ResponseEntity<>(movieService.findMovieById(movieId), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "search/{subTitleMovie}/page/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> findAllMovieWhereSubtitleMatchTitle(@PathVariable String subTitleMovie, @PathVariable int pageNumber){
        try {
            return new ResponseEntity<>(movieService.findAllMoviesForSubTitleMatchTitle(subTitleMovie, pageNumber), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "upcoming/page/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> findAllUpcomingMovies(@PathVariable int pageNumber){
        try {
            return new ResponseEntity<>(movieService.getAllUpcomingMovies(pageNumber), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "recommendations/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> findRecommendationMovies(@PathVariable String userId){
        try {
            return new ResponseEntity<>(movieService.get10BestMovieFit(userId), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
