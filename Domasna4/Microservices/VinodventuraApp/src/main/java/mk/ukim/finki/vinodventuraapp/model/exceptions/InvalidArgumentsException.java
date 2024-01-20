package mk.ukim.finki.vinodventuraapp.model.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("All fields are required. Please fill in all the information.");
    }
}
