package ServiceTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ro.ubb.model.Genre;
import ro.ubb.model.Movie;
import ro.ubb.model.dto.GenreDTO;
import ro.ubb.model.exceptions.ValidationException;
import ro.ubb.persistence.MovieRepository;
import ro.ubb.service.MovieService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MovieServiceTest {

    private static MovieService movieService;

    @BeforeAll
    public static void setUp(){
        movieService = new MovieService(new MovieRepository());
    }

    @Test
    public void testFindAllMovieForTheCurrentYear(){
        try{
            List<Movie> movies = movieService.findAllMovieForTheCurrentYear(1);
            assertTrue(movies.size() > 0);
            try{
                movieService.findAllMovieForTheCurrentYear(900000000);
                fail();
            }catch (ValidationException e){
                assertTrue(true);
            }
        }catch (JsonProcessingException e){
            fail();
        }
    }

    @Test
    public void testFindAllGenres(){
        try {
            List<Genre> genres = movieService.findAllGenres();
            assertTrue(genres.size() > 0);
            assertTrue(genres.stream()
                    .anyMatch(genre -> genre.getName().equals("Action")));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindAllMoviesForAGenre(){
        try {
            List<Genre> genres = movieService.findAllGenres();
            for (Genre genre: genres) {
                assertTrue(movieService.findAllMoviesForAGenre(genre.getId(), 1).size() > 0);
            }
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindMovieById(){
        try {
            List<Genre> genres = movieService.findAllGenres();
            assertTrue(genres.size() > 0);
            Movie movie = movieService.findAllMoviesForAGenre(genres.get(0).getId(), 1).get(0);
            assertNotNull(movie);
            Movie movie1 = movieService.findMovieById(movie.getId());
            assertEquals(movie.getId(), movie1.getId());
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindAllGenresAndCountMovies(){
        try {
            List<GenreDTO> genres = movieService.findAllGenresAndCountMovies();
            assertTrue(genres.size() > 0);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindAllMoviesForSubTitleMatchTitle(){
        try{
            List<Movie> movies = movieService.findAllMoviesForSubTitleMatchTitle("a", 1);
            assertTrue(movies.size() > 0);
            try{
                movieService.findAllMoviesForSubTitleMatchTitle("a", 9000000);
                fail();
            }catch (ValidationException e){
                assertTrue(true);
            }
        }catch (JsonProcessingException e){
            fail();
        }
    }

    @Test
    public void testGetAllUpcomingMovies(){
        try{
            List<Movie> movies = movieService.getAllUpcomingMovies(1);
            assertTrue(movies.size() > 0);
            try{
                movieService.getAllUpcomingMovies(900000);
                fail();
            }catch (ValidationException e){
                assertTrue(true);
            }
        }catch (JsonProcessingException e){
            fail();
        }
    }

    @Test
    public void testGet10BestMovieFit(){
        try {
           List<Movie> movies = movieService.get10BestMovieFit("1");
            assertEquals(10, movies.size());
        } catch (JsonProcessingException e) {
            fail();
        }
    }
}
