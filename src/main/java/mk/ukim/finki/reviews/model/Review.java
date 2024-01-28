package mk.ukim.finki.reviews.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    private String description;
    private Long winery_id;
    private LocalDateTime timestamp;
    private Long user_id;

    public Review() {
    }

    public Review(Integer score, String description, Long winery, LocalDateTime timestamp, Long user) {
        this.score = score;
        this.description = description;
        this.winery_id = winery;
        this.timestamp = timestamp;
        this.user_id = user;
    }
}
