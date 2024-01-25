package mk.ukim.finki.reviews.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.reviews.model.Review;
import mk.ukim.finki.reviews.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/winery/{id}")
    public List<Review> showReview(
            @PathVariable Long id) {
        List<Review> reviewList = new ArrayList<>();
        reviewList = reviewService.allReviews(id);
        return reviewList;
    }

}
