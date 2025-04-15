package lk.ac.iit.bookstore.repository;

import lk.ac.iit.bookstore.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// In-memory repository for Order entity
public class OrderRepository {
    private static final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private static OrderRepository instance;
    
    private OrderRepository() {
    }
    
    public static synchronized OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }
    
    // Create a new order
    public Order createOrder(Order order) {
        Long id = idCounter.getAndIncrement();
        order.setId(id);
        orders.put(id, order);
        return order;
    }
    
    // Get all orders
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    // Get an order by ID
    public Order getOrderById(Long id) {
        return orders.get(id);
    }
    
    // Get orders for a customer
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orders.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
    
    // Get a customer's order by ID
    public Order getCustomerOrderById(Long customerId, Long orderId) {
        Order order = orders.get(orderId);
        if (order != null && order.getCustomerId().equals(customerId)) {
            return order;
        }
        return null;
    }
    
    // Update an order
    public Order updateOrder(Long id, Order order) {
        if (orders.containsKey(id)) {
            order.setId(id);
            orders.put(id, order);
            return order;
        }
        return null;
    }
    
    // Delete an order
    public boolean deleteOrder(Long id) {
        return orders.remove(id) != null;
    }
    
    // Check if an order exists
    public boolean existsById(Long id) {
        return orders.containsKey(id);
    }
}