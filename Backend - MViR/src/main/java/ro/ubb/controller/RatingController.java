package ro.ubb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.model.dto.RatingDTO;
import ro.ubb.model.exceptions.ValidationException;
import ro.ubb.service.RatingService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/data")
    public void handlePostRequest(@RequestBody String data) {
        try {
            ratingService.insertRating(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/history/user/{userId}/page/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<RatingDTO>> findAllRatingsForAUser(@PathVariable String userId, @PathVariable int pageNumber){
        return new ResponseEntity<>(ratingService.findAllRatingsForAUser(userId, pageNumber), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody String data) {
        try {
            ratingService.deleteRating(data);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (ValidationException e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
