package ModelTest;

import org.junit.jupiter.api.Test;
import ro.ubb.model.Rating;
import ro.ubb.model.dto.RatingDTO;

import static org.junit.jupiter.api.Assertions.*;

public class RatingDTOTest {
    @Test
    public void testRatingDTOConstructor() {
        RatingDTO ratingDTOCreateWAllArgsConstructor = new RatingDTO(new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname"), "movieTitle");
        RatingDTO ratingDTOCreateWAllArgsConstructorDef = new RatingDTO(1, 1, 12, "10/10/2023", "10:12", "hostname", "movieTitle");
        RatingDTO ratingDTOCreateWNoArgsConstructor = new RatingDTO();

        assertEquals(1, ratingDTOCreateWAllArgsConstructor.getId());
        assertEquals(1, ratingDTOCreateWAllArgsConstructor.getMovieId());
        assertEquals(12, ratingDTOCreateWAllArgsConstructor.getTimeSpend());
        assertEquals("10/10/2023", ratingDTOCreateWAllArgsConstructor.getStartDate());
        assertEquals("10:12", ratingDTOCreateWAllArgsConstructor.getStartTime());
        assertEquals("hostname", ratingDTOCreateWAllArgsConstructor.getHostname());
        assertEquals("movieTitle", ratingDTOCreateWAllArgsConstructor.getMovieTitle());

        assertEquals(1, ratingDTOCreateWAllArgsConstructorDef.getId());
        assertEquals(1, ratingDTOCreateWAllArgsConstructorDef.getMovieId());
        assertEquals(12, ratingDTOCreateWAllArgsConstructorDef.getTimeSpend());
        assertEquals("10/10/2023", ratingDTOCreateWAllArgsConstructorDef.getStartDate());
        assertEquals("10:12", ratingDTOCreateWAllArgsConstructorDef.getStartTime());
        assertEquals("hostname", ratingDTOCreateWAllArgsConstructorDef.getHostname());
        assertEquals("movieTitle", ratingDTOCreateWAllArgsConstructorDef.getMovieTitle());


        assertEquals(0, ratingDTOCreateWNoArgsConstructor.getId());
        assertEquals(0, ratingDTOCreateWNoArgsConstructor.getMovieId());
        assertEquals(0, ratingDTOCreateWNoArgsConstructor.getTimeSpend());
        assertNull(ratingDTOCreateWNoArgsConstructor.getStartDate());
        assertNull(ratingDTOCreateWNoArgsConstructor.getStartTime());
        assertNull(ratingDTOCreateWNoArgsConstructor.getHostname());
        assertNull(ratingDTOCreateWNoArgsConstructor.getMovieTitle());
    }

    @Test
    public void testRatingDTOSettersAndGetters() {
        RatingDTO ratingDTOCreateWAllArgsConstructor = new RatingDTO(new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname"), "movieTitle");

        assertEquals(1, ratingDTOCreateWAllArgsConstructor.getId());
        ratingDTOCreateWAllArgsConstructor.setId(2);
        assertEquals(2, ratingDTOCreateWAllArgsConstructor.getId());

        assertEquals(1, ratingDTOCreateWAllArgsConstructor.getMovieId());
        ratingDTOCreateWAllArgsConstructor.setMovieId(2);
        assertEquals(2, ratingDTOCreateWAllArgsConstructor.getMovieId());

        assertEquals(12, ratingDTOCreateWAllArgsConstructor.getTimeSpend());
        ratingDTOCreateWAllArgsConstructor.setTimeSpend(10);
        assertEquals(10, ratingDTOCreateWAllArgsConstructor.getTimeSpend());

        assertEquals("10/10/2023", ratingDTOCreateWAllArgsConstructor.getStartDate());
        ratingDTOCreateWAllArgsConstructor.setStartDate("9/10/2023");
        assertEquals("9/10/2023", ratingDTOCreateWAllArgsConstructor.getStartDate());

        assertEquals("10:12", ratingDTOCreateWAllArgsConstructor.getStartTime());
        ratingDTOCreateWAllArgsConstructor.setStartTime("10:10");
        assertEquals("10:10", ratingDTOCreateWAllArgsConstructor.getStartTime());

        assertEquals("hostname", ratingDTOCreateWAllArgsConstructor.getHostname());
        ratingDTOCreateWAllArgsConstructor.setHostname("SecondHostname");
        assertEquals("SecondHostname", ratingDTOCreateWAllArgsConstructor.getHostname());

        assertEquals("movieTitle", ratingDTOCreateWAllArgsConstructor.getMovieTitle());
        ratingDTOCreateWAllArgsConstructor.setMovieTitle("SecondMovieTitle");
        assertEquals("SecondMovieTitle", ratingDTOCreateWAllArgsConstructor.getMovieTitle());
    }

    @Test
    public void testRatingDTOEqualsMethod() {
        RatingDTO ratingDTOCreateWAllArgsConstructor1 = new RatingDTO(new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname"), "movieTitle");
        RatingDTO ratingDTOCreateWAllArgsConstructor2 = new RatingDTO(new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname"), "movieTitle1");
        RatingDTO ratingDTOCreateWAllArgsConstructor3 = new RatingDTO(new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname"), "movieTitle");

        assertNotEquals(ratingDTOCreateWAllArgsConstructor1, ratingDTOCreateWAllArgsConstructor2);
        assertNotEquals(ratingDTOCreateWAllArgsConstructor3, ratingDTOCreateWAllArgsConstructor2);
        assertEquals(ratingDTOCreateWAllArgsConstructor1, ratingDTOCreateWAllArgsConstructor3);
    }
}
