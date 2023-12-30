package ro.ubb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.ubb.model.Movie;
import ro.ubb.model.Rating;
import ro.ubb.model.dto.RatingDTO;
import ro.ubb.model.exceptions.ValidationException;
import ro.ubb.persistence.MovieRepository;
import ro.ubb.persistence.RatingRepository;
import com.google.gson.reflect.TypeToken;
import ro.ubb.utils.SocketTextHandler;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final static double MIN_SECONDS = 1;
    private final static double MAX_SECONDS = 300; // 5 minutes in seconds
    private final static double MIN_RATING = 1;
    private final static double MAX_RATING = 5;

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final SocketTextHandler socketTextHandler;


    private void validateRating(Rating rating){
        StringBuilder builder = new StringBuilder();
        if (rating.getTimeSpend() < 1){
            builder.append("TimeSpend invalid!\n");
        }
        if (rating.getUserId().isEmpty()){
            builder.append("UserId invalid!\n");
        }
        if (!builder.isEmpty()) {
            throw new ValidationException(builder.toString());
        }
    }

    private double calculateRating(int timeSpend){
        return  ((timeSpend - MIN_SECONDS) / (MAX_SECONDS - MIN_SECONDS)) * (MAX_RATING - MIN_RATING) + MIN_RATING;
    }

    public void insertRating(String dataJSON) throws IOException {
        JSONObject obj = new JSONObject(dataJSON);
        Rating rating = null;
        Gson gson = new Gson();
        rating = gson.fromJson(dataJSON, Rating.class);
        rating.setRating(calculateRating(rating.getTimeSpend()));
        Movie movie = this.movieRepository.findBestMatchMovieWithSubName(obj.getString("title"));
        if (movie != null){
            rating.setMovieId(movie.getId());
            validateRating(rating);
            ratingRepository.save(rating);
        }
        if (this.socketTextHandler != null){
            this.socketTextHandler.notifyClient(rating.getUserId());
        }
    }

    public List<RatingDTO> findAllRatingsForAUser(String userId, int pageNumber) {
        List<RatingDTO> dtos = new ArrayList<>();
        System.out.println(userId + "  " + PageRequest.of(pageNumber, 20));
        System.out.println(ratingRepository.findByUserIdWithPagination(userId, PageRequest.of(pageNumber, 20)));
        for (Rating rating : ratingRepository.findByUserIdWithPagination(userId, PageRequest.of(pageNumber, 20))) {
            Movie movie = null;
            try {
                movie = movieRepository.findById(rating.getMovieId());
                dtos.add(new RatingDTO(rating, movie.getName()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        return dtos.stream()
                .sorted(Comparator.comparing(RatingDTO::getStartTime).reversed())
                .collect(Collectors.toList());
    }

    public void deleteRating(String ratingsId){
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Integer>>() {
            }.getType();
            List<Integer> ids = gson.fromJson(ratingsId, listType);
            ids.forEach(this.ratingRepository::deleteById);
        }catch (JsonSyntaxException e){
            throw new ValidationException("Rating ids list is unexpected!");
        }
    }
}
