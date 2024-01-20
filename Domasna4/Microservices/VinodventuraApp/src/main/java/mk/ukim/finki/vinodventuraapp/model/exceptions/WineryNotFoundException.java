package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class WineryNotFoundException extends RuntimeException{
    public WineryNotFoundException(Long id) {
        super(String.format("Winery with id %d was not found",id));
    }
}
