package mk.ukim.finki.wishlists.service.impl;

import mk.ukim.finki.user.model.User;
import mk.ukim.finki.wineries.model.Winery;
import mk.ukim.finki.wishlists.model.WishList;
import mk.ukim.finki.vinodventuraapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.WineryAlreadyInWishListException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.WineryNotFoundException;
import mk.ukim.finki.user.repository.UserRepository;
import mk.ukim.finki.wishlists.repository.WishListRepository;
import mk.ukim.finki.wineries.service.WineryService;
import mk.ukim.finki.wishlists.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;

    private final UserRepository userRepository;

    private final WineryService wineryService;

    public WishListServiceImpl(WishListRepository wishListRepository, UserRepository userRepository, WineryService wineryService) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.wineryService = wineryService;
    }

    @Override
    public WishList getWishListForUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(InvalidUserCredentialsException::new);
        return wishListRepository.findByUser(user).orElseGet(() ->
        {
            WishList wishList = new WishList(user);
            return this.wishListRepository.save(wishList);
        });
    }

    @Override
    public WishList addToWishlist(User user, Long wineryId) {
        WishList wishList = getWishListForUser(user);
        Winery winery = this.wineryService.findById(wineryId)
                .orElseThrow(() -> new WineryNotFoundException(wineryId));
        List<Winery> wineriesInWishList = wishList.getWineries().stream()
                .filter(i -> i.getId().equals(wineryId)).toList();
        if (wineriesInWishList.size() > 0)
            throw new WineryAlreadyInWishListException();
        wishList.getWineries().add(winery);
        return this.wishListRepository.save(wishList);
    }

    @Override
    public void removeFromWishList(User user, Long wineryId) {
        WishList wishList = getWishListForUser(user);
        Winery winery = this.wineryService.findById(wineryId)
                .orElseThrow(() -> new WineryNotFoundException(wineryId));
        wishList.getWineries().remove(winery);
        this.wishListRepository.save(wishList);
    }

}
