package ControllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;
import ro.ubb.controller.RatingController;
import ro.ubb.model.dto.RatingDTO;
import ro.ubb.service.RatingService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;

    private List<RatingDTO> ratingDTOList;

    @BeforeEach
    public void setUpAtr(){
        MockitoAnnotations.openMocks(this);
        ratingController = new RatingController(ratingService);
        RatingDTO ratingDTO = new RatingDTO(); ratingDTO.setId(1);
        ratingDTOList = new ArrayList<>(List.of(ratingDTO));
    }

    @Test
    public void testFindAllRatingsForAUser(){
        when(ratingService.findAllRatingsForAUser("1", 1)).thenReturn(ratingDTOList);
        assertSame(HttpStatus.OK, ratingController.findAllRatingsForAUser("1", 1).getStatusCode());
        assertEquals(1, ratingController.findAllRatingsForAUser("1", 1).getBody().size());
    }


}
