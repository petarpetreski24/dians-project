package mk.ukim.finki.reviews.service.impl;

import mk.ukim.finki.reviews.model.Review;
import mk.ukim.finki.reviews.repository.ReviewRepository;
import mk.ukim.finki.reviews.service.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> allReviews(Long wineryId) {
        return reviewRepository.findByWinery_idEquals(wineryId);
    }
}
