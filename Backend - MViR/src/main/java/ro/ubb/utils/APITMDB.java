package ro.ubb.utils;

import ro.ubb.model.Genre;
import ro.ubb.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class APITMDB {

    private static final String accessKey = "a04b56e2d76f3788a52a57a3c4ec7fff";
    private static final String photosURL = "https://image.tmdb.org/t/p/original";

    public static String getURLRequestForFindMovieById(int id){
        return "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + accessKey;
    }

    public static String getURLRequestForFindAllGenres(){
        return "https://api.themoviedb.org/3/genre/movie/list?api_key=" + accessKey;
    }

    public static String getURLRequestForFindNumberOfMovieForAGenre(Genre genre){
        return "https://api.themoviedb.org/3/discover/movie?api_key=" + accessKey + "&with_genres=" + genre.getId();
    }

    public static String getURLRequestForFindMoviesByNameContainSubName(String subName, int pageNumber){
        subName = subName.replace(" ", "%20");
        return "https://api.themoviedb.org/3/search/movie?api_key=" + accessKey + "&query=" + subName + "&page=" + pageNumber;
    }

    public static String getURLRequestForFindAllMovieForAGenre(int pageNumber, int genreId){
        return "https://api.themoviedb.org/3/discover/movie?api_key=" + accessKey + "&with_genres=" + genreId + "&page=" + pageNumber;
    }

    public static String getURLRequestForFindAllMoviesForAYear(int year, int pageNumber){
        return "https://api.themoviedb.org/3/discover/movie?api_key=" + accessKey + "&page=" + pageNumber + "&primary_release_year=" + year;
    }

    public static String getURLRequestForFindAllUpcomingMovies(int pageNumber){
        return "https://api.themoviedb.org/3/movie/upcoming?api_key=" + accessKey + "&page=" + pageNumber;
    }

    public static int getMovieId(Map<String, Object> map){
        return Integer.parseInt(String.valueOf(map.get("id")));
    }

    public static String getMovieOriginalTitle(Map<String, Object> map){
        return String.valueOf(map.get("original_title"));
    }

    public static String getMovieOverview(Map<String, Object> map){
        return String.valueOf(map.get("overview"));
    }

    public static String getMovieBannerURL(Map<String, Object> map){
        return photosURL + map.get("backdrop_path");
    }

    public static String getMoviePosterURL(Map<String, Object> map){
        return photosURL + map.get("poster_path");
    }

    public static String getMovieReleaseDate(Map<String, Object> map){
        return String.valueOf(map.get("release_date"));
    }

    public static List<Genre> getGenresFromAStringJson(String stringJson){
        List<Genre> genres = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{id=(\\d+), name=(\\w+)\\}");
        Matcher matcher = pattern.matcher(stringJson);
        while (matcher.find()) {
            genres.add(new Genre(Integer.parseInt(matcher.group(1)), matcher.group(2)));
        }
        return genres;
    }

    private static String getObjectKeyValueForStringFromJSON(String key, JSONObject movieObject){
        if (movieObject.has(key) && !movieObject.isNull(key)) {
            return movieObject.getString(key);
        }
        return "";
    }

    private static int getObjectKeyValueForIntegerFromJSON(String key, JSONObject movieObject){
        if (movieObject.has(key) && !movieObject.isNull(key)) {
            return movieObject.getInt(key);
        }
        return -1;
    }

    public static List<Movie> getMoviesFromSearchResults(String jsonString){
        List<Movie> movies = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonString);
        int page = jsonObject.getInt("page");
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieObject = results.getJSONObject(i);
            int id = getObjectKeyValueForIntegerFromJSON("id", movieObject);
            String originalTitle = getObjectKeyValueForStringFromJSON("original_title", movieObject);
            String overview = getObjectKeyValueForStringFromJSON("overview", movieObject);
            String posterPath = photosURL + getObjectKeyValueForStringFromJSON("poster_path", movieObject);
            String backdropPath = photosURL + getObjectKeyValueForStringFromJSON("backdrop_path", movieObject);
            String releaseDate = getObjectKeyValueForStringFromJSON("release_date", movieObject);
            movies.add(new Movie(id, originalTitle, overview, posterPath, backdropPath, null, releaseDate));
        }
        return movies;
    }

    public static List<Genre> getMovieGenres(Map<String, Object> map){
        return getGenresFromAStringJson(String.valueOf(map.get("genres")));
    }

    public static int getNumberOfMoviesForAGenre(Map<String, Object> map){
        return Integer.parseInt(String.valueOf(map.get("total_results")));
    }

    public static int getNumberOfPages(Map<String, Object> map){
        return Integer.parseInt(String.valueOf(map.get("total_pages")));
    }

    public static Movie getFirstMovieSearchByName(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray results = jsonObject.getJSONArray("results");
        if (results.length() > 0){
            JSONObject movieObject = results.getJSONObject(0);
            int id = getObjectKeyValueForIntegerFromJSON("id", movieObject);
            String originalTitle = getObjectKeyValueForStringFromJSON("original_title", movieObject);
            String overview = getObjectKeyValueForStringFromJSON("overview", movieObject);
            String posterPath = photosURL + getObjectKeyValueForStringFromJSON("poster_path", movieObject);
            String backdropPath = photosURL + getObjectKeyValueForStringFromJSON("backdrop_path", movieObject);
            String releaseDate = getObjectKeyValueForStringFromJSON("release_date", movieObject);
            return new Movie(id, originalTitle, overview, posterPath, backdropPath, null, releaseDate);
        }
        return null;
    }

}
