package ro.ubb.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import ro.ubb.model.Genre;
import ro.ubb.model.Movie;
import org.springframework.stereotype.Repository;
import ro.ubb.utils.APIRequest;
import ro.ubb.utils.APITMDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    public Movie findById(int id) throws JsonProcessingException {
        String result = APIRequest.getRequest(APITMDB.getURLRequestForFindMovieById(id));
        Map<String, Object> map = APIRequest.getMapFromStringJSON(result);
        return new Movie(APITMDB.getMovieId(map),
                APITMDB.getMovieOriginalTitle(map),
                APITMDB.getMovieOverview(map),
                APITMDB.getMoviePosterURL(map),
                APITMDB.getMovieBannerURL(map),
                APITMDB.getMovieGenres(map),
                APITMDB.getMovieReleaseDate(map));
    }

    public List<Genre> getAllGenres() throws JsonProcessingException {
        String genres = APIRequest.getRequest(APITMDB.getURLRequestForFindAllGenres());
        Map<String, Object> map = APIRequest.getMapFromStringJSON(genres);
        return new ArrayList<Genre>(APITMDB.getGenresFromAStringJson(String.valueOf(map.get("genres"))));
    }

    public int getNumberOfMoviesForAGenre(Genre genre) throws JsonProcessingException {
        String genres = APIRequest.getRequest(APITMDB.getURLRequestForFindNumberOfMovieForAGenre(genre));
        Map<String, Object> map = APIRequest.getMapFromStringJSON(genres);
        return APITMDB.getNumberOfMoviesForAGenre(map);
    }

    public List<Movie> getMoviesBySubName(String subName, int pageNumber) throws JsonProcessingException {
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindMoviesByNameContainSubName(subName, pageNumber));
        return APITMDB.getMoviesFromSearchResults(moviesJSON);
    }

    public List<Movie> getMoviesByGenre(int genreId, int pageNumber){
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindAllMovieForAGenre(pageNumber, genreId));
        return APITMDB.getMoviesFromSearchResults(moviesJSON);
    }

    public List<Movie> getMoviesByYear(int year, int pageNumber){
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindAllMoviesForAYear(year, pageNumber));
        return APITMDB.getMoviesFromSearchResults(moviesJSON);
    }

    public int getNumberOfPagesForMoviesInAYear(int year) throws JsonProcessingException {
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindAllMoviesForAYear(year, 1));
        Map<String, Object> map = APIRequest.getMapFromStringJSON(moviesJSON);
        return APITMDB.getNumberOfPages(map);
    }

    public int getNumberOfPagesForMoviesWithASpecificGenre(int genreId) throws JsonProcessingException {
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindAllMovieForAGenre(1, genreId));
        Map<String, Object> map = APIRequest.getMapFromStringJSON(moviesJSON);
        return APITMDB.getNumberOfPages(map);
    }

    public int getNumberOfPagesForMoviesWhereSubTitleMatchTitle(String subTitle) throws JsonProcessingException {
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindMoviesByNameContainSubName(subTitle, 1));
        Map<String, Object> map = APIRequest.getMapFromStringJSON(moviesJSON);
        return APITMDB.getNumberOfPages(map);
    }

    public int getNumberOfPagesForUpcomingMovies() throws JsonProcessingException {
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindAllUpcomingMovies( 1));
        Map<String, Object> map = APIRequest.getMapFromStringJSON(moviesJSON);
        return APITMDB.getNumberOfPages(map);
    }

    public List<Movie> getAllUpcomingMovies(int pageNumber){
        String moviesJSON = APIRequest.getRequest(APITMDB.getURLRequestForFindAllUpcomingMovies(pageNumber));
        return APITMDB.getMoviesFromSearchResults(moviesJSON);
    }

    public Movie findBestMatchMovieWithSubName(String subName){
        return APITMDB.getFirstMovieSearchByName(APIRequest.getRequest(APITMDB.getURLRequestForFindMoviesByNameContainSubName(subName, 1)));
    }
}
