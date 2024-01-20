package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class WineryAlreadyInWishListException extends RuntimeException {
    public WineryAlreadyInWishListException() {
        super("Winery is already in wish list");
    }
}
