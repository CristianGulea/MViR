package UtilsClassTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import ro.ubb.utils.RecommenderServiceRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecommenderServiceRequestTest {

    @Test
    public void testGetRecommendationMovieIds(){
        try {
            List<Integer> ids = RecommenderServiceRequest.getRecommendationMovieIds("1");
            assertEquals(10, ids.size());
        } catch (JsonProcessingException e) {
            fail();
        }

    }
}
