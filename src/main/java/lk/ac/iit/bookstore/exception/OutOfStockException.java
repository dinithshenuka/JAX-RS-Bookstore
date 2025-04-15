package lk.ac.iit.bookstore.exception;

// Exception for when a book is out of stock
public class OutOfStockException extends RuntimeException {
    
    public OutOfStockException(String message) {
        super(message);
    }
    
    public OutOfStockException(Long bookId) {
        super("Book with ID " + bookId + " is out of stock.");
    }
    
    public OutOfStockException(Long bookId, int requested, int available) {
        super("Not enough stock for book with ID " + bookId + ". Requested: " + requested + ", Available: " + available);
    }
}