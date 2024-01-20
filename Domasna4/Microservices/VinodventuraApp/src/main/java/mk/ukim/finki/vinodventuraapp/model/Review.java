package mk.ukim.finki.vinodventuraapp.model;

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
    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;
    private LocalDateTime timestamp;
    @ManyToOne
    private User user;

    public Review() {
    }

    public Review(Integer score, String description, Winery winery, LocalDateTime timestamp, User user) {
        this.score = score;
        this.description = description;
        this.winery = winery;
        this.timestamp = timestamp;
        this.user = user;
    }
}
