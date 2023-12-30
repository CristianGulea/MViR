package ModelTest;

import org.junit.jupiter.api.Test;
import ro.ubb.model.Rating;

import static org.junit.jupiter.api.Assertions.*;

public class RatingTest {
    @Test
    public void testRatingConstructor() {
        Rating ratingCreateWAllArgsConstructor = new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname");
        Rating ratingCreateWNoArgsConstructor = new Rating();

        assertEquals(1, ratingCreateWAllArgsConstructor.getId());
        assertEquals("userId", ratingCreateWAllArgsConstructor.getUserId());
        assertEquals(1, ratingCreateWAllArgsConstructor.getMovieId());
        assertEquals(1.1f, ratingCreateWAllArgsConstructor.getRating());
        assertEquals(12, ratingCreateWAllArgsConstructor.getTimeSpend());
        assertEquals("10/10/2023", ratingCreateWAllArgsConstructor.getStartDate());
        assertEquals("10:12", ratingCreateWAllArgsConstructor.getStartTime());
        assertEquals("hostname", ratingCreateWAllArgsConstructor.getHostname());

        assertEquals(0, ratingCreateWNoArgsConstructor.getId());
        assertNull(ratingCreateWNoArgsConstructor.getUserId());
        assertEquals(0, ratingCreateWNoArgsConstructor.getMovieId());
        assertEquals(0f, ratingCreateWNoArgsConstructor.getRating());
        assertEquals(0, ratingCreateWNoArgsConstructor.getTimeSpend());
        assertNull(ratingCreateWNoArgsConstructor.getStartDate());
        assertNull(ratingCreateWNoArgsConstructor.getStartTime());
        assertNull(ratingCreateWNoArgsConstructor.getHostname());
    }

    @Test
    public void testRatingSettersAndGetters() {
        Rating ratingCreateWAllArgsConstructor = new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname");

        assertEquals(1, ratingCreateWAllArgsConstructor.getId());
        ratingCreateWAllArgsConstructor.setId(2);
        assertEquals(2, ratingCreateWAllArgsConstructor.getId());

        assertEquals("userId", ratingCreateWAllArgsConstructor.getUserId());
        ratingCreateWAllArgsConstructor.setUserId("SecondUserId");
        assertEquals("SecondUserId", ratingCreateWAllArgsConstructor.getUserId());

        assertEquals(1, ratingCreateWAllArgsConstructor.getMovieId());
        ratingCreateWAllArgsConstructor.setMovieId(2);
        assertEquals(2, ratingCreateWAllArgsConstructor.getMovieId());

        assertEquals(1.1f, ratingCreateWAllArgsConstructor.getRating());
        ratingCreateWAllArgsConstructor.setRating(6.2f);
        assertEquals(6.2f, ratingCreateWAllArgsConstructor.getRating());

        assertEquals(12, ratingCreateWAllArgsConstructor.getTimeSpend());
        ratingCreateWAllArgsConstructor.setTimeSpend(10);
        assertEquals(10, ratingCreateWAllArgsConstructor.getTimeSpend());

        assertEquals("10/10/2023", ratingCreateWAllArgsConstructor.getStartDate());
        ratingCreateWAllArgsConstructor.setStartDate("9/10/2023");
        assertEquals("9/10/2023", ratingCreateWAllArgsConstructor.getStartDate());

        assertEquals("10:12", ratingCreateWAllArgsConstructor.getStartTime());
        ratingCreateWAllArgsConstructor.setStartTime("10:10");
        assertEquals("10:10", ratingCreateWAllArgsConstructor.getStartTime());

        assertEquals("hostname", ratingCreateWAllArgsConstructor.getHostname());
        ratingCreateWAllArgsConstructor.setHostname("SecondHostname");
        assertEquals("SecondHostname", ratingCreateWAllArgsConstructor.getHostname());
    }

    @Test
    public void testRatingEqualsMethod() {
        Rating ratingCreateWAllArgsConstructor1 = new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname");
        Rating ratingCreateWAllArgsConstructor2 = new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:10", "hostname");
        Rating ratingCreateWAllArgsConstructor3 = new Rating(1, "userId", 1, 1.1f, 12
                , "10/10/2023", "10:12", "hostname");

        assertNotEquals(ratingCreateWAllArgsConstructor1, ratingCreateWAllArgsConstructor2);
        assertNotEquals(ratingCreateWAllArgsConstructor3, ratingCreateWAllArgsConstructor2);
        assertEquals(ratingCreateWAllArgsConstructor1, ratingCreateWAllArgsConstructor3);
    }
}
