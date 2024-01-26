package mk.ukim.finki.reviews.service;

import mk.ukim.finki.reviews.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    public List<Review> allReviews(Long winery);
}
