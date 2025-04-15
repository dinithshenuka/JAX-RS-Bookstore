package lk.ac.iit.bookstore.repository;

import lk.ac.iit.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerRepository {
    private static final Map<Long, Customer> customers = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private static CustomerRepository instance;
    
    private CustomerRepository() {
    }
    
    public static synchronized CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }
    
    // Create a new customer
    public Customer createCustomer(Customer customer) {
        Long id = idCounter.getAndIncrement();
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }
    
    // Get all customers
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    
    // Get a customer by ID
    public Customer getCustomerById(Long id) {
        return customers.get(id);
    }
    
    // Update a customer
    public Customer updateCustomer(Long id, Customer customer) {
        if (customers.containsKey(id)) {
            customer.setId(id);
            customers.put(id, customer);
            return customer;
        }
        return null;
    }
    
    // Delete a customer
    public boolean deleteCustomer(Long id) {
        return customers.remove(id) != null;
    }
    
    // Check if a customer exists by ID
    public boolean existsById(Long id) {
        return customers.containsKey(id);
    }
    
    // Check if a customer exists by email
    public boolean existsByEmail(String email) {
        return customers.values().stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }
}