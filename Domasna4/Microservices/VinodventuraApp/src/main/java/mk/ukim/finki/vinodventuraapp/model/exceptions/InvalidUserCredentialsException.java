package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("The username or password is not correct. Please try again.");
    }
}
