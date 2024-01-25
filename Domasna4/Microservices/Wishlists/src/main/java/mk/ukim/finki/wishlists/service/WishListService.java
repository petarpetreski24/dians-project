package mk.ukim.finki.wishlists.service;

import mk.ukim.finki.user.model.User;
import mk.ukim.finki.wishlists.model.WishList;

public interface WishListService {
    WishList getWishListForUser(User user);
    WishList addToWishlist(User user, Long wineryId);
    void removeFromWishList(User user, Long wineryId);
}
