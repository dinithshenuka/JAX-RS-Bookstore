package lk.ac.iit.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lk.ac.iit.bookstore.exception.notfound.BookNotFoundException;
import lk.ac.iit.bookstore.exception.notfound.CartNotFoundException;
import lk.ac.iit.bookstore.exception.notfound.CustomerNotFoundException;
import lk.ac.iit.bookstore.exception.notfound.OrderNotFoundException;
import lk.ac.iit.bookstore.exception.validation.InvalidInputException;
import lk.ac.iit.bookstore.exception.validation.OutOfStockException;
import lk.ac.iit.bookstore.model.*;
import lk.ac.iit.bookstore.repository.BookRepository;
import lk.ac.iit.bookstore.repository.CartRepository;
import lk.ac.iit.bookstore.repository.CustomerRepository;
import lk.ac.iit.bookstore.repository.OrderRepository;

import java.net.URI;
import java.util.List;

// REST resource for Order operations
@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    
    private final OrderRepository orderRepository = OrderRepository.getInstance();
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();
    private final CartRepository cartRepository = CartRepository.getInstance();
    private final BookRepository bookRepository = BookRepository.getInstance();
    
    @Context
    private UriInfo uriInfo;
    
    // Create a new order from customer's cart
    @POST
    public Response createOrder(@PathParam("customerId") Long customerId) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Get cart
        Cart cart = cartRepository.getCart(customerId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new CartNotFoundException("Cart for customer with ID " + customerId + " is empty or does not exist");
        }
        
        // Create new order
        Order order = new Order();
        order.setCustomerId(customerId);
        
        // Process each cart item
        for (CartItem cartItem : cart.getItems()) {
            Book book = bookRepository.getBookById(cartItem.getBookId());
            
            // Check if book exists
            if (book == null) {
                throw new BookNotFoundException(cartItem.getBookId());
            }
            
            // Check stock
            if (book.getStock() < cartItem.getQuantity()) {
                throw new OutOfStockException(book.getId(), cartItem.getQuantity(), book.getStock());
            }
            
            // Add to order
            OrderItem orderItem = new OrderItem(
                book.getId(),
                book.getTitle(),
                cartItem.getQuantity(),
                book.getPrice()
            );
            order.addItem(orderItem);
            
            // Update stock
            book.setStock(book.getStock() - cartItem.getQuantity());
            bookRepository.updateBook(book.getId(), book);
        }
        
        // Save order
        Order createdOrder = orderRepository.createOrder(order);
        
        // Clear cart
        cartRepository.clearCart(customerId);
        
        // Return created response
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdOrder.getId())).build();
        return Response.created(uri).entity(createdOrder).build();
    }
    
    // Get all customer orders
    @GET
    public List<Order> getCustomerOrders(@PathParam("customerId") Long customerId) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        return orderRepository.getOrdersByCustomer(customerId);
    }
    
    // Get specific customer order by ID
    @GET
    @Path("/{orderId}")
    public Order getCustomerOrderById(@PathParam("customerId") Long customerId,
                                     @PathParam("orderId") Long orderId) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        Order order = orderRepository.getCustomerOrderById(customerId, orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId, customerId);
        }
        
        return order;
    }
}