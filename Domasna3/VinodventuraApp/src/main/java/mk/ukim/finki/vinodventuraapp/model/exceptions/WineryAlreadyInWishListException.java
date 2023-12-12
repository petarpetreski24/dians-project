package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class WineryAlreadyInWishListException extends RuntimeException {
    public WineryAlreadyInWishListException() {
        super("The winery you are trying to add, is already in your wishlist.");
    }
}
