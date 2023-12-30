package ro.ubb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import ro.ubb.model.Genre;
import ro.ubb.model.Movie;
import ro.ubb.model.dto.GenreDTO;
import ro.ubb.model.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import ro.ubb.persistence.MovieRepository;
import ro.ubb.utils.RecommenderServiceRequest;

import java.util.ArrayList;
import java.util.List;
import java.time.Year;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private void validatePageNumber(int pageNumber, int maxPageNumber){
        if (pageNumber < 1 || pageNumber > maxPageNumber){
            throw new ValidationException("CurrentPage is invalid");
        }
    }

    private void validateSubTitle(String subTitle){
        if (subTitle.isEmpty()){
            throw new ValidationException("CurrentPage is invalid");
        }
    }

    public List<Movie> findAllMovieForTheCurrentYear(int pageNumber) throws JsonProcessingException {
        int currentYear = Integer.parseInt(String.valueOf(Year.now())) - 1;
        int maxPage = movieRepository.getNumberOfPagesForMoviesInAYear(currentYear);
        validatePageNumber(pageNumber, maxPage);
        return movieRepository.getMoviesByYear(currentYear, pageNumber);
    }

    public List<Genre> findAllGenres() throws JsonProcessingException {
        return movieRepository.getAllGenres();
    }

    public List<Movie> findAllMoviesForAGenre(int genreId, int pageNumber) throws JsonProcessingException {
        int maxPage = movieRepository.getNumberOfPagesForMoviesWithASpecificGenre(genreId);
        validatePageNumber(pageNumber, maxPage);
        return movieRepository.getMoviesByGenre(genreId, pageNumber);
    }

    public Movie findMovieById(int movieId) throws JsonProcessingException {
        return movieRepository.findById(movieId);
    }

    public List<GenreDTO> findAllGenresAndCountMovies() throws JsonProcessingException {
        List<GenreDTO> genreDTOS = new ArrayList<>();
        List<Genre> genres = movieRepository.getAllGenres();
        for (Genre genre : genres){
            //int numberOfMoviesForAGenre = movieRepository.getNumberOfMoviesForAGenre(genre);
            genreDTOS.add(new GenreDTO(genre.getId(), genre.getName(), 0));
        }
        return genreDTOS;
    }

    public List<Movie> findAllMoviesForSubTitleMatchTitle(String subTitle, int pageNumber) throws JsonProcessingException {
        validateSubTitle(subTitle);
        int maxPages = movieRepository.getNumberOfPagesForMoviesWhereSubTitleMatchTitle(subTitle);
        validatePageNumber(pageNumber, maxPages);
        return movieRepository.getMoviesBySubName(subTitle, pageNumber);
    }

    public List<Movie> getAllUpcomingMovies(int pageNumber) throws JsonProcessingException {
        int maxPage = movieRepository.getNumberOfPagesForUpcomingMovies();
        validatePageNumber(pageNumber, maxPage);
        return movieRepository.getAllUpcomingMovies(pageNumber);
    }

    public List<Movie> get10BestMovieFit(String userId) throws JsonProcessingException {
        List<Movie> movies = new ArrayList<>();
        for (int movieId : RecommenderServiceRequest.getRecommendationMovieIds(userId)){
            movies.add(findMovieById(movieId));
        }
        return movies;
    }
}
