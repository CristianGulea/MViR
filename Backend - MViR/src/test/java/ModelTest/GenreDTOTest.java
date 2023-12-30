package ModelTest;

import org.junit.jupiter.api.Test;
import ro.ubb.model.dto.GenreDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenreDTOTest {
    @Test
    public void testGenreConstructor() {
        GenreDTO genreDTOCreateWAllArgsConstructor = new GenreDTO(1, "Action", 10);
        GenreDTO genreDTOCreateWNoArgsConstructor = new GenreDTO();

        assertEquals(1, genreDTOCreateWAllArgsConstructor.getId());
        assertEquals("Action", genreDTOCreateWAllArgsConstructor.getName());
        assertEquals(10, genreDTOCreateWAllArgsConstructor.getNumberOfMovies());

        assertEquals(0, genreDTOCreateWNoArgsConstructor.getId());
        assertNull(genreDTOCreateWNoArgsConstructor.getName());
        assertEquals(0, genreDTOCreateWNoArgsConstructor.getNumberOfMovies());
    }

    @Test
    public void testGenreDTOSettersAndGetters() {
        GenreDTO genreDTOCreateWAllArgsConstructor = new GenreDTO(1, "Action", 10);

        assertEquals(1, genreDTOCreateWAllArgsConstructor.getId());
        genreDTOCreateWAllArgsConstructor.setId(2);
        assertEquals(2, genreDTOCreateWAllArgsConstructor.getId());

        assertEquals("Action", genreDTOCreateWAllArgsConstructor.getName());
        genreDTOCreateWAllArgsConstructor.setName("Thriller");
        assertEquals("Thriller", genreDTOCreateWAllArgsConstructor.getName());

        assertEquals(10, genreDTOCreateWAllArgsConstructor.getNumberOfMovies());
        genreDTOCreateWAllArgsConstructor.setNumberOfMovies(9);
        assertEquals(9, genreDTOCreateWAllArgsConstructor.getNumberOfMovies());
    }

    @Test
    public void testGenreEqualsMethod(){
        GenreDTO genreDTOCreateWAllArgsConstructor1 = new GenreDTO(1, "Action",10);
        GenreDTO genreDTOCreateWAllArgsConstructor2 = new GenreDTO(2, "Thriller", 10);
        GenreDTO genreDTOCreateWAllArgsConstructor3 = new GenreDTO(1, "Action", 10);

        assertNotEquals(genreDTOCreateWAllArgsConstructor1, genreDTOCreateWAllArgsConstructor2);
        assertNotEquals(genreDTOCreateWAllArgsConstructor3, genreDTOCreateWAllArgsConstructor2);
        assertEquals(genreDTOCreateWAllArgsConstructor1, genreDTOCreateWAllArgsConstructor3);
    }
}
