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

    public Review() {
    }
}
