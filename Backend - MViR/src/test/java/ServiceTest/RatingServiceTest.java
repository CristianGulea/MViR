package ServiceTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ro.ubb.model.Movie;
import ro.ubb.model.Rating;
import ro.ubb.model.dto.RatingDTO;
import ro.ubb.persistence.MovieRepository;
import ro.ubb.persistence.RatingRepository;
import ro.ubb.service.RatingService;
import ro.ubb.utils.SocketTextHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private RatingService ratingService;

    private List<Rating> ratings;

    private Page<Rating> ratingsPage;

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
        ratingService = new RatingService(ratingRepository, movieRepository, new SocketTextHandler());
        Rating rating1 = new Rating(); rating1.setId(1); rating1.setUserId("1"); rating1.setMovieId(100); rating1.setStartTime("10:12");
        Rating rating2 = new Rating(); rating2.setId(2); rating2.setUserId("1"); rating2.setMovieId(100); rating2.setStartTime("10:12");
        Rating rating3 = new Rating(); rating3.setId(3); rating3.setUserId("1"); rating3.setMovieId(100); rating3.setStartTime("10:15");
        ratings = new ArrayList<>(List.of(rating1, rating2, rating3));
        ratingsPage = new PageImpl<>(ratings);
    }


    @Test
    public void testFindAllRatingsForAUser(){
        Movie movie = new Movie(); movie.setId(ratings.get(0).getMovieId());
        when(ratingRepository.findByUserIdWithPagination("1", PageRequest.of(1, 20))).thenReturn(ratingsPage);
        try {
            when(movieRepository.findById(ratings.get(0).getMovieId())).thenReturn(movie);
        } catch (JsonProcessingException e) {
            fail();
        }
        List<RatingDTO> ratingDTOList = ratingService.findAllRatingsForAUser("1", 1);
        assertEquals(3, ratingDTOList.size());
        assertEquals(3, ratingDTOList.get(0).getId());
    }

}
