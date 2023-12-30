package ModelTest;

import org.junit.jupiter.api.Test;
import ro.ubb.model.Genre;

import static org.junit.jupiter.api.Assertions.*;

public class GenreTest {
    @Test
    public void testGenreConstructor() {
        Genre genreCreateWAllArgsConstructor = new Genre(1, "Action");
        Genre genreCreateWNoArgsConstructor = new Genre();

        assertEquals(1, genreCreateWAllArgsConstructor.getId());
        assertEquals("Action", genreCreateWAllArgsConstructor.getName());

        assertEquals(0, genreCreateWNoArgsConstructor.getId());
        assertNull(genreCreateWNoArgsConstructor.getName());
    }

    @Test
    public void testGenreSettersAndGetters() {
        Genre genreCreateWAllArgsConstructor = new Genre(1, "Action");

        assertEquals(1, genreCreateWAllArgsConstructor.getId());
        genreCreateWAllArgsConstructor.setId(2);
        assertEquals(2, genreCreateWAllArgsConstructor.getId());

        assertEquals("Action", genreCreateWAllArgsConstructor.getName());
        genreCreateWAllArgsConstructor.setName("Thriller");
        assertEquals("Thriller", genreCreateWAllArgsConstructor.getName());
    }

    @Test
    public void testGenreEqualsMethod(){
        Genre genreCreateWAllArgsConstructor1 = new Genre(1, "Action");
        Genre genreCreateWAllArgsConstructor2 = new Genre(2, "Thriller");
        Genre genreCreateWAllArgsConstructor3 = new Genre(1, "Action");

        assertNotEquals(genreCreateWAllArgsConstructor1, genreCreateWAllArgsConstructor2);
        assertNotEquals(genreCreateWAllArgsConstructor3, genreCreateWAllArgsConstructor2);
        assertEquals(genreCreateWAllArgsConstructor1, genreCreateWAllArgsConstructor3);
    }
}
