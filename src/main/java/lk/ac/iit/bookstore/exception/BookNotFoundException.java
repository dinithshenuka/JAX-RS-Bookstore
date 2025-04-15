package lk.ac.iit.bookstore.exception;

// Exception for when a book is not found
public class BookNotFoundException extends RuntimeException {
    
    public BookNotFoundException(String message) {
        super(message);
    }
    
    public BookNotFoundException(Long id) {
        super("Book with ID " + id + " does not exist.");
    }
}