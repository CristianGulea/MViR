package ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.ubb.controller.MovieController;
import ro.ubb.model.Genre;
import ro.ubb.model.Movie;
import ro.ubb.persistence.MovieRepository;
import ro.ubb.service.MovieService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class MovieControllerTest {

    private static MovieController movieController;
    private static MovieService movieService;

    @BeforeAll
    public static void setUp(){
        movieService = new MovieService(new MovieRepository());
        movieController = new MovieController(movieService);
    }

    @Test
    public void testFindAllMoviesForAYear(){
        assertSame(movieController.findAllMoviesForAYear(1).getStatusCode(), HttpStatus.OK);
        assertTrue(Objects.requireNonNull(movieController.findAllMoviesForAYear(1).getBody()).size() > 0);
        assertSame(movieController.findAllMoviesForAYear(9000000).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testFindAllGenres(){
        assertSame(movieController.findAllGenres().getStatusCode(), HttpStatus.OK);
        assertTrue(Objects.requireNonNull(movieController.findAllGenres().getBody()).size() > 0);
    }

    @Test
    public void testFindAllMoviesWithASpecificGenre(){
        try {
            Genre genre = movieService.findAllGenres().get(0);
            assertSame(movieController.findAllMoviesWithASpecificGenre(1, genre.getId()).getStatusCode(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindMovieById(){
        try {
            Movie movie = movieService.findAllMoviesForSubTitleMatchTitle("a", 1).get(0);
            assertSame(movieController.findMovieById(movie.getId()).getStatusCode(), HttpStatus.OK);
            assertEquals(Objects.requireNonNull(movieController.findMovieById(movie.getId()).getBody()).getId(), movie.getId());
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindAllUpcomingMovies(){
        ResponseEntity<List<Movie>> response = movieController.findAllUpcomingMovies(1);
        assertSame(response.getStatusCode(), HttpStatus.OK);
        assertTrue(Objects.requireNonNull(response.getBody()).size() > 0);
    }

    @Test
    public void testFindRecommendationMovies(){
        ResponseEntity<List<Movie>> response = movieController.findRecommendationMovies("1");
        assertSame(response.getStatusCode(), HttpStatus.OK);
        assertEquals(10, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void testFindAllMovieWhereSubtitleMatchTitle(){
        assertSame(movieController.findAllMovieWhereSubtitleMatchTitle("a", 1).getStatusCode(), HttpStatus.OK);
        assertTrue(Objects.requireNonNull(movieController.findAllMovieWhereSubtitleMatchTitle("a", 1).getBody()).size() > 0);
    }
}
