package mk.ukim.finki.vinodventuraapp.repository;

import mk.ukim.finki.vinodventuraapp.bootstrap.DataHolder;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import mk.ukim.finki.vinodventuraapp.model.enumerations.WishListStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WishListRepository {

    public Optional<WishList> findById(Long id) {
        return DataHolder.wishLists.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<WishList> findByUsernameAndStatus(String username, WishListStatus status) {
        return DataHolder.wishLists.stream()
                .filter(i -> i.getUser().getUsername().equals(username)
                        && i.getStatus().equals(status))
                .findFirst();
    }

    public WishList save(WishList shoppingCart) {
        DataHolder.wishLists
                .removeIf(i -> i.getUser().getUsername()
                        .equals(shoppingCart.getUser().getUsername()));
        DataHolder.wishLists.add(shoppingCart);
        return shoppingCart;
    }
}

