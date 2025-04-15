package lk.ac.iit.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lk.ac.iit.bookstore.exception.BookNotFoundException;
import lk.ac.iit.bookstore.exception.CartNotFoundException;
import lk.ac.iit.bookstore.exception.CustomerNotFoundException;
import lk.ac.iit.bookstore.exception.InvalidInputException;
import lk.ac.iit.bookstore.model.Book;
import lk.ac.iit.bookstore.model.Cart;
import lk.ac.iit.bookstore.model.CartItem;
import lk.ac.iit.bookstore.repository.BookRepository;
import lk.ac.iit.bookstore.repository.CartRepository;
import lk.ac.iit.bookstore.repository.CustomerRepository;

// REST resource for Cart operations
@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    
    private final CartRepository cartRepository = CartRepository.getInstance();
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();
    private final BookRepository bookRepository = BookRepository.getInstance();
    
    // Add an item to cart
    @POST
    @Path("/items")
    public Cart addItemToCart(@PathParam("customerId") Long customerId, CartItem cartItem) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Validate cart item
        if (cartItem.getBookId() == null) {
            throw new InvalidInputException("Book ID cannot be null");
        }
        
        if (cartItem.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero");
        }
        
        // Validate book exists
        Book book = bookRepository.getBookById(cartItem.getBookId());
        if (book == null) {
            throw new BookNotFoundException(cartItem.getBookId());
        }
        
        // Get or create cart for customer
        Cart cart = cartRepository.getOrCreateCart(customerId);
        
        // Add item to cart
        cart.addItem(cartItem);
        
        // Save cart
        return cartRepository.saveCart(cart);
    }
    
    // Get customer's cart
    @GET
    public Cart getCart(@PathParam("customerId") Long customerId) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Get cart
        Cart cart = cartRepository.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException(customerId);
        }
        
        return cart;
    }
    
    // Update an item in cart
    @PUT
    @Path("/items/{bookId}")
    public Cart updateCartItem(@PathParam("customerId") Long customerId, 
                               @PathParam("bookId") Long bookId,
                               CartItem cartItem) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Get cart
        Cart cart = cartRepository.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException(customerId);
        }
        
        // Validate book exists
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        
        // Validate cart item
        if (cartItem.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero");
        }
        
        // Get existing cart item
        CartItem existingItem = cart.getItem(bookId);
        if (existingItem == null) {
            throw new InvalidInputException("Book with ID " + bookId + " not in cart");
        }
        
        // Update quantity
        existingItem.setQuantity(cartItem.getQuantity());
        
        // Save cart
        return cartRepository.saveCart(cart);
    }
    
    // Remove an item from cart
    @DELETE
    @Path("/items/{bookId}")
    public Response removeCartItem(@PathParam("customerId") Long customerId,
                                  @PathParam("bookId") Long bookId) {
        // Validate customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        
        // Get cart
        Cart cart = cartRepository.getCart(customerId);
        if (cart == null) {
            throw new CartNotFoundException(customerId);
        }
        
        // Validate book exists
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        
        // Remove item
        boolean removed = cart.removeItem(bookId);
        if (!removed) {
            throw new InvalidInputException("Book with ID " + bookId + " not in cart");
        }
        
        // Save cart
        cartRepository.saveCart(cart);
        
        return Response.noContent().build();
    }
}