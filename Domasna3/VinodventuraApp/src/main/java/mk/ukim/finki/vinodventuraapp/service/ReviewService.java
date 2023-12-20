package mk.ukim.finki.vinodventuraapp.service;

import mk.ukim.finki.vinodventuraapp.model.Review;

import java.util.Optional;

public interface ReviewService {
    public Optional<Review> addReview(Integer score, String description, Long wineryId, Long userId);
}
