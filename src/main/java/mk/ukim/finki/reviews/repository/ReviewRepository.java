package mk.ukim.finki.reviews.repository;

import mk.ukim.finki.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByWinery_idEquals(Long winery);
    List<Review> findByScoreGreaterThanEqual(Integer score);
    List<Review> findByTimestampAfter(LocalDateTime date);

}
