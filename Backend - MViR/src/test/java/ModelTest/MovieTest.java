package ModelTest;

import org.junit.jupiter.api.Test;
import ro.ubb.model.Genre;
import ro.ubb.model.Movie;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    @Test
    public void testMovieConstructor() {
        Movie movieCreateWAllArgsConstructor = new Movie(1, "Name", "overview", "urlPoster"
                , "urlBanner", new ArrayList<>(), "10/10/2022");
        Movie movieCreateWNoArgsConstructor = new Movie();

        assertEquals(1, movieCreateWAllArgsConstructor.getId());
        assertEquals("Name", movieCreateWAllArgsConstructor.getName());
        assertEquals("overview", movieCreateWAllArgsConstructor.getOverview());
        assertEquals("urlPoster", movieCreateWAllArgsConstructor.getUrlPoster());
        assertEquals("urlBanner", movieCreateWAllArgsConstructor.getUrlBanner());
        assertEquals(0, movieCreateWAllArgsConstructor.getGenres().size());
        assertEquals("10/10/2022", movieCreateWAllArgsConstructor.getReleaseDate());

        assertEquals(0, movieCreateWNoArgsConstructor.getId());
        assertNull(movieCreateWNoArgsConstructor.getName());
        assertNull(movieCreateWNoArgsConstructor.getOverview());
        assertNull(movieCreateWNoArgsConstructor.getUrlPoster());
        assertNull(movieCreateWNoArgsConstructor.getUrlBanner());
        assertNull(movieCreateWNoArgsConstructor.getGenres());
        assertNull(movieCreateWNoArgsConstructor.getReleaseDate());
    }

    @Test
    public void testMovieSettersAndGetters() {
        Movie movieCreateWAllArgsConstructor = new Movie(1, "Name", "overview", "urlPoster"
                , "urlBanner", new ArrayList<>(), "10/10/2022");

        assertEquals(1, movieCreateWAllArgsConstructor.getId());
        movieCreateWAllArgsConstructor.setId(2);
        assertEquals(2, movieCreateWAllArgsConstructor.getId());

        assertEquals("Name", movieCreateWAllArgsConstructor.getName());
        movieCreateWAllArgsConstructor.setName("SecondName");
        assertEquals("SecondName", movieCreateWAllArgsConstructor.getName());

        assertEquals("overview", movieCreateWAllArgsConstructor.getOverview());
        movieCreateWAllArgsConstructor.setOverview("SecondOverview");
        assertEquals("SecondOverview", movieCreateWAllArgsConstructor.getOverview());

        assertEquals("urlPoster", movieCreateWAllArgsConstructor.getUrlPoster());
        movieCreateWAllArgsConstructor.setUrlPoster("SecondUrlPoster");
        assertEquals("SecondUrlPoster", movieCreateWAllArgsConstructor.getUrlPoster());

        assertEquals("urlBanner", movieCreateWAllArgsConstructor.getUrlBanner());
        movieCreateWAllArgsConstructor.setUrlBanner("SecondUrlBanner");
        assertEquals("SecondUrlBanner", movieCreateWAllArgsConstructor.getUrlBanner());

        assertEquals(0, movieCreateWAllArgsConstructor.getGenres().size());
        movieCreateWAllArgsConstructor.setGenres(new ArrayList<>(List.of(new Genre(1, "Action"))));
        assertEquals(1, movieCreateWAllArgsConstructor.getGenres().size());

        assertEquals("10/10/2022", movieCreateWAllArgsConstructor.getReleaseDate());
        movieCreateWAllArgsConstructor.setReleaseDate("11/11/2022");
        assertEquals("11/11/2022", movieCreateWAllArgsConstructor.getReleaseDate());
    }

    @Test
    public void testMovieEqualsMethod() {
        Movie movieCreateWAllArgsConstructor1 = new Movie(1, "Name", "overview", "urlPoster"
                , "urlBanner", new ArrayList<>(), "10/10/2022");
        Movie movieCreateWAllArgsConstructor2 = new Movie(1, "Name2", "overview", "urlPoster"
                , "urlBanner", new ArrayList<>(), "10/10/2022");
        Movie movieCreateWAllArgsConstructor3 = new Movie(1, "Name", "overview", "urlPoster"
                , "urlBanner", new ArrayList<>(), "10/10/2022");

        assertNotEquals(movieCreateWAllArgsConstructor1, movieCreateWAllArgsConstructor2);
        assertNotEquals(movieCreateWAllArgsConstructor3, movieCreateWAllArgsConstructor2);
        assertEquals(movieCreateWAllArgsConstructor1, movieCreateWAllArgsConstructor3);
    }

}
