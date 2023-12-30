package ro.ubb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ubb.model.Rating;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingDTO {
    private int id;
    private int movieId;
    private int timeSpend;
    private String startDate;
    private String startTime;
    private String hostname;
    private String movieTitle;


    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public RatingDTO(Rating rating, String movieTitle) {
        this(rating.getId(), rating.getMovieId(), rating.getTimeSpend(), rating.getStartDate(), rating.getStartTime(), rating.getHostname(), movieTitle);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDTO ratingDTO = (RatingDTO) o;
        return id == ratingDTO.id && movieId == ratingDTO.movieId && timeSpend == ratingDTO.timeSpend && Objects.equals(startDate, ratingDTO.startDate) && Objects.equals(startTime, ratingDTO.startTime) && Objects.equals(hostname, ratingDTO.hostname) && Objects.equals(movieTitle, ratingDTO.movieTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, timeSpend, startDate, startTime, hostname, movieTitle);
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", timeSpend=" + timeSpend +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
