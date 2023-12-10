package mk.ukim.finki.vinodventuraapp.service.impl;

import mk.ukim.finki.vinodventuraapp.model.Review;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.exceptions.WineryNotFoundException;
import mk.ukim.finki.vinodventuraapp.repository.ReviewRepository;
import mk.ukim.finki.vinodventuraapp.repository.WineryRepository;
import mk.ukim.finki.vinodventuraapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final WineryRepository wineryRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, WineryRepository wineryRepository) {
        this.reviewRepository = reviewRepository;
        this.wineryRepository = wineryRepository;
    }

    @Override
    public Optional<Review> addReview(Integer score, String description, Long wineryId) {
        Winery winery = wineryRepository.findById(wineryId)
                .orElseThrow(() -> new WineryNotFoundException(wineryId));
        Review review = new Review(score, description, winery, LocalDateTime.now());
        return Optional.of(reviewRepository.save(review));
    }
}
