package mk.ukim.finki.reviews.service;

import mk.ukim.finki.reviews.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {
    public List<Review> allReviewsByWineryId(Long winery);
    public Review findById(Long id);
    List<Review> findByScoreGreaterThan(Integer score);
    List<Review> findByAllAfterDate(LocalDateTime date);
    List<Review> findAll();
}
