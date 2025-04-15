package lk.ac.iit.bookstore.model;

// Item in a customer order
public class OrderItem {
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private double price;

    // Constructors
    public OrderItem() {
    }

    public OrderItem(Long bookId, String bookTitle, int quantity, double price) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Helper method to calculate the item total
    public double getTotal() {
        return price * quantity;
    }
}