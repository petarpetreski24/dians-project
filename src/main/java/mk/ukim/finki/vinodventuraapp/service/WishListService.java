package mk.ukim.finki.vinodventuraapp.service;

import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import mk.ukim.finki.vinodventuraapp.model.enumerations.WishListStatus;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    WishList getWishListForUser(User user);
    WishList addToWishlist(User user, Long wineryId);
    void removeFromWishList(User user, Long wineryId);
}
