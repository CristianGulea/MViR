package ro.ubb.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import ro.ubb.model.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findAllByUserId(String userId);

    @Query("SELECT e FROM Rating e WHERE e.userId = ?1")
    Page<Rating> findByUserIdWithPagination(String id, Pageable pageable);
}
