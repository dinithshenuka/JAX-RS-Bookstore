package lk.ac.iit.bookstore.exception;

// Exception for when an order is not found
public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException(String message) {
        super(message);
    }
    
    public OrderNotFoundException(Long id) {
        super("Order with ID " + id + " does not exist.");
    }
    
    public OrderNotFoundException(Long orderId, Long customerId) {
        super("Order with ID " + orderId + " not found for customer with ID " + customerId);
    }
}