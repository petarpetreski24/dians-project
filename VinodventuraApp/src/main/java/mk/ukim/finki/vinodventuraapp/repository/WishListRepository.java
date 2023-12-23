package mk.ukim.finki.vinodventuraapp.repository;

import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long> {

    Optional<WishList> findByUser(User user);
}
