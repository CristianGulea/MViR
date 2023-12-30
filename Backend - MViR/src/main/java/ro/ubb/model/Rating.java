package ro.ubb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ratingn")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private int movieId;
    private double rating;
    private int timeSpend;
    private String startDate;
    private String startTime;
    private String hostname;

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", movieId=" + movieId +
                ", rating=" + rating +
                ", timeSpend=" + timeSpend +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return id == rating1.id && movieId == rating1.movieId && Double.compare(rating1.rating, rating) == 0 && timeSpend == rating1.timeSpend && Objects.equals(userId, rating1.userId) && Objects.equals(startDate, rating1.startDate) && Objects.equals(startTime, rating1.startTime) && Objects.equals(hostname, rating1.hostname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, movieId, rating, timeSpend, startDate, startTime, hostname);
    }
}
