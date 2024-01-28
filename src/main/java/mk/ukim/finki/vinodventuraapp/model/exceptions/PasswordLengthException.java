package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class PasswordLengthException extends RuntimeException{
    public PasswordLengthException() {
        super("Password must be at least 8 characters long.");
    }
}
