package mk.ukim.finki.reviews.repository;

import mk.ukim.finki.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByWinery(Long winery);
}
