package ro.ubb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class RecommenderServiceRequest {

    private static final String URL_REQUEST = "http://127.0.0.1:8000/best-10-fit/";

    private static List<Integer> getMovieIdsFromJSON(String movieIdsJSON) throws JsonProcessingException {
        Map<String, Object> map = APIRequest.getMapFromStringJSON(movieIdsJSON);
        return (List<Integer>) map.get("movies");
    }

    public static List<Integer> getRecommendationMovieIds(String userId) throws JsonProcessingException {
        String movieIdsJSON = APIRequest.getRequest(URL_REQUEST +  userId);
        return getMovieIdsFromJSON(movieIdsJSON);
    }
}
