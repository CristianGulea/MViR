package PersistenceTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ro.ubb.model.Genre;
import ro.ubb.model.Movie;
import ro.ubb.persistence.MovieRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieRepositoryTest {

    private static MovieRepository movieRepository;

    @BeforeAll
    public static void setUp(){
        movieRepository = new MovieRepository();
    }

    @Test
    public void testGetAllGenres(){
        try {
            List<Genre> genres = movieRepository.getAllGenres();
            assertTrue(genres.size() > 0);
            assertTrue(genres.stream()
                    .anyMatch(genre -> genre.getName().equals("Action")));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testGetNumberOfMoviesForAGenre(){
        try {
            List<Genre> genres = movieRepository.getAllGenres();
            for (Genre genre : genres) {
                int numberOfMoviesForAGenre = movieRepository.getNumberOfMoviesForAGenre(genre);
                assertTrue(numberOfMoviesForAGenre > 0);
            }
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testGetMoviesBySubName(){
        try {
            List<Movie> movies = movieRepository.getMoviesBySubName("a", 1);
            assertTrue(movies.size() > 0);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testGetMoviesByYear(){
        List<Movie> movies = movieRepository.getMoviesByYear(2021, 1);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void testGetNumberOfPagesForMoviesInAYear(){
        try {
            int numberOfPages = movieRepository.getNumberOfPagesForMoviesInAYear(2023);
            assertTrue(numberOfPages > 0);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testGetNumberOfPagesForMoviesWhereSubTitleMatchTitle(){
        try {
            int numberOfPages = movieRepository.getNumberOfPagesForMoviesWhereSubTitleMatchTitle("a");
            assertTrue(numberOfPages > 0);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testGetNumberOfPagesForUpcomingMovies(){
        try {
            int numberOfPages = movieRepository.getNumberOfPagesForUpcomingMovies();
            assertTrue(numberOfPages > 0);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testGetAllUpcomingMovies(){
        List<Movie> movies = movieRepository.getAllUpcomingMovies(1);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void testFindBestMatchMovieWithSubName(){
        Movie movie = movieRepository.findBestMatchMovieWithSubName("a");
        assertNotNull(movie);
    }

    @Test
    public void testGetNumberOfPagesForMoviesWithASpecificGenre(){
        try {
            List<Genre> genres = movieRepository.getAllGenres();
            int numberOfPages = movieRepository.getNumberOfPagesForMoviesWithASpecificGenre(genres.get(0).getId());
            assertTrue(numberOfPages > 0);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testFindById(){
        try {
            Movie movie = movieRepository.findBestMatchMovieWithSubName("a");
            assertNotNull(movie);
            Movie movie1 = movieRepository.findById(movie.getId());
            assertEquals(movie.getId(), movie1.getId());
        } catch (JsonProcessingException e) {
            fail();
        }
    }






}
