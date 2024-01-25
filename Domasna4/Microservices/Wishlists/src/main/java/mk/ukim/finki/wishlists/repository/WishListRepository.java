package mk.ukim.finki.wishlists.repository;

import mk.ukim.finki.user.model.User;
import mk.ukim.finki.wishlists.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long> {

    Optional<WishList> findByUser(User user);
}
