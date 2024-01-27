package mk.ukim.finki.reviews.service.impl;

import mk.ukim.finki.reviews.model.Review;
import mk.ukim.finki.reviews.repository.ReviewRepository;
import mk.ukim.finki.reviews.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> allReviewsByWineryId(Long wineryId) {
        return reviewRepository.findByWinery_idEquals(wineryId);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Review> findByScoreGreaterThan(Integer score) {
        return reviewRepository.findByScoreGreaterThanEqual(score);
    }

    @Override
    public List<Review> findByAllAfterDate(LocalDateTime date) {
        return reviewRepository.findByTimestampAfter(date);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
