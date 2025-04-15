package lk.ac.iit.bookstore.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Customer order
public class Order {
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;

    // Constructors
    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Order(Long id, Long customerId) {
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Helper methods
    public void addItem(OrderItem item) {
        this.items.add(item);
        calculateTotal();
    }

    public void calculateTotal() {
        this.totalAmount = this.items.stream()
                .mapToDouble(OrderItem::getTotal)
                .sum();
    }
}