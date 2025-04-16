package lk.ac.iit.bookstore.exception.validation;

// Exception thrown when input data is invalid
public class InvalidInputException extends RuntimeException {
    
    public InvalidInputException(String message) {
        super(message);
    }
}