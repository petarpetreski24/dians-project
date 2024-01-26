package mk.ukim.finki.reviews.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.reviews.model.Review;
import mk.ukim.finki.reviews.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/winery/{id}")
    public List<Review> showReview(
            @PathVariable Long id) {
        List<Review> reviewList = new ArrayList<>();
        reviewList = reviewService.allReviews(id);
        return reviewList;
    }

    @PostMapping("/add/winery/{id}")
    public ResponseEntity<String> addReview(
            @RequestParam Integer score,
            @RequestParam String description,
            @RequestParam(required = false) Long userId,
            @PathVariable Long id) {

        if (userId == null) {
            return ResponseEntity.badRequest().body("Invalid user");
        }

        reviewService.addReview(score, description, id, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");
    }

}
