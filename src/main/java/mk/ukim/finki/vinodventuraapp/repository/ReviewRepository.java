package mk.ukim.finki.vinodventuraapp.repository;

import mk.ukim.finki.vinodventuraapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
