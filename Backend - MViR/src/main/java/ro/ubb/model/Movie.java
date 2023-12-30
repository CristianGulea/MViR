package ro.ubb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie implements Serializable {
    private int id;
    private String name;
    private String overview;
    private String urlPoster;
    private String urlBanner;
    private List<Genre> genres;
    private String releaseDate;


    private StringBuilder transformMapGenresToString(){
        StringBuilder genres = new StringBuilder();
        if (this.genres != null) {
            for (Genre genre : this.genres) {
                genres.append(" ").append(genre.getName());
            }
        }
        return genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", urlPoster='" + urlPoster + '\'' +
                ", urlBanner='" + urlBanner + '\'' +
                ", genres=" + transformMapGenresToString() +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Objects.equals(name, movie.name) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(urlPoster, movie.urlPoster) &&
                Objects.equals(urlBanner, movie.urlBanner) &&
                Objects.equals(genres, movie.genres) &&
                Objects.equals(releaseDate, movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, overview, urlPoster, urlBanner, genres, releaseDate);
    }
}
