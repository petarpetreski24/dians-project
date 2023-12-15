package mk.ukim.finki.vinodventuraapp.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add/winery/{id}")
    public String addReview(@RequestParam Integer score, @RequestParam String description, @RequestParam(required = false) Long userId,
                            @PathVariable Long id, Model model) {
        if (userId == null){
            return "redirect:/winery/details/" + id+"?error=user";
        }
        reviewService.addReview(score, description, id,userId);
        return "redirect:/winery/details/" + id;
    }
}
