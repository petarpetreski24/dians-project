package mk.ukim.finki.vinodventuraapp.service;

import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.WishList;

public interface WishListService {
    WishList getWishListForUser(User user);
    WishList addToWishlist(User user, Long wineryId);
    void removeFromWishList(User user, Long wineryId);
}
