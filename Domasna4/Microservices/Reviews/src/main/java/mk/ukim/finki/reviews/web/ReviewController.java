package mk.ukim.finki.reviews.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.reviews.model.Review;
import mk.ukim.finki.reviews.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/byWinery/{id}")
    public List<Review> getReviewsByWinery(@PathVariable Long id) {
        return reviewService.allReviewsByWineryId(id);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/score/{score}")
    public List<Review> getAllReviews(@PathVariable Integer score) {
        return reviewService.findByScoreGreaterThan(score);
    }

    @GetMapping("/afterDate")
    public List<Review> getAllReviews(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                          LocalDateTime date) {
        return reviewService.findByAllAfterDate(date);
    }
}
