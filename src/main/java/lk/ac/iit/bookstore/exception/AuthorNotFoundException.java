package lk.ac.iit.bookstore.exception;

// Exception for when an author is not found
public class AuthorNotFoundException extends RuntimeException {
    
    public AuthorNotFoundException(String message) {
        super(message);
    }
    
    public AuthorNotFoundException(Long id) {
        super("Author with ID " + id + " does not exist.");
    }
}