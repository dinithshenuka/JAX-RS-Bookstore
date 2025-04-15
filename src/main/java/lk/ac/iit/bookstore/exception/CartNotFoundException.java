package lk.ac.iit.bookstore.exception;

// Exception for when a cart is not found
public class CartNotFoundException extends RuntimeException {
    
    public CartNotFoundException(String message) {
        super(message);
    }
    
    public CartNotFoundException(Long customerId) {
        super("Cart for customer with ID " + customerId + " does not exist.");
    }
}