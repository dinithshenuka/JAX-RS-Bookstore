package lk.ac.iit.bookstore.model;

import java.util.ArrayList;
import java.util.List;

// Customer's shopping cart 
public class Cart {
    private Long customerId;
    private List<CartItem> items;

    // Constructors
    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(Long customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Helper methods
    public void addItem(CartItem item) {
        // Check if item already exists in cart
        for (CartItem existingItem : items) {
            if (existingItem.getBookId().equals(item.getBookId())) {
                // Update quantity if book already in cart
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // If book not in cart, add new item
        items.add(item);
    }

    public boolean removeItem(Long bookId) {
        return items.removeIf(item -> item.getBookId().equals(bookId));
    }

    public CartItem getItem(Long bookId) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                return item;
            }
        }
        return null;
    }

    public void clear() {
        items.clear();
    }
}