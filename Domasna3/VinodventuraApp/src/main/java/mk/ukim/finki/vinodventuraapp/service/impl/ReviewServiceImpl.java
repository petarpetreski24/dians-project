package mk.ukim.finki.vinodventuraapp.service.impl;

import mk.ukim.finki.vinodventuraapp.model.Review;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.exceptions.WineryNotFoundException;
import mk.ukim.finki.vinodventuraapp.repository.ReviewRepository;
import mk.ukim.finki.vinodventuraapp.repository.UserRepository;
import mk.ukim.finki.vinodventuraapp.repository.WineryRepository;
import mk.ukim.finki.vinodventuraapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final WineryRepository wineryRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, WineryRepository wineryRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.wineryRepository = wineryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Review> addReview(Integer score, String description, Long wineryId, Long userId) {
        Winery winery = wineryRepository.findById(wineryId)
                .orElseThrow(() -> new WineryNotFoundException(wineryId));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        Review review = new Review(score, description, winery, LocalDateTime.now(),user);
        return Optional.of(reviewRepository.save(review));
    }
}
