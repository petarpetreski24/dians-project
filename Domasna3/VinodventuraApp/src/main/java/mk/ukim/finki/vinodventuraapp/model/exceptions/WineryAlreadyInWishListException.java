package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class WineryAlreadyInWishListException extends RuntimeException{
    public WineryAlreadyInWishListException() {
        super("Selected winery is already in your wishlist.");
    }
}
