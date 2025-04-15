package lk.ac.iit.bookstore.repository;

import lk.ac.iit.bookstore.model.Cart;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// repository for Cart entity
public class CartRepository {
    private static final Map<Long, Cart> carts = new ConcurrentHashMap<>();
    
    private static CartRepository instance;
    
    private CartRepository() {
    }
    
    public static synchronized CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }
    
    // Creates or gets a cart for a customer
    public Cart getOrCreateCart(Long customerId) {
        return carts.computeIfAbsent(customerId, Cart::new);
    }
    
    // Gets a customer's cart
    public Cart getCart(Long customerId) {
        return carts.get(customerId);
    }
    
    // Saves a cart
    public Cart saveCart(Cart cart) {
        carts.put(cart.getCustomerId(), cart);
        return cart;
    }
    
    // Clears a customer's cart
    public void clearCart(Long customerId) {
        Cart cart = carts.get(customerId);
        if (cart != null) {
            cart.clear();
        }
    }
    
    // Removes a customer's cart
    public void removeCart(Long customerId) {
        carts.remove(customerId);
    }
}