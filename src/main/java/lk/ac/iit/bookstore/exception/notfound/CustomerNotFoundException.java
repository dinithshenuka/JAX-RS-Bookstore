package lk.ac.iit.bookstore.exception.notfound;

// Exception for when a customer is not found
public class CustomerNotFoundException extends RuntimeException {
    
    public CustomerNotFoundException(String message) {
        super(message);
    }
    
    public CustomerNotFoundException(Long id) {
        super("Customer with ID " + id + " does not exist.");
    }
}