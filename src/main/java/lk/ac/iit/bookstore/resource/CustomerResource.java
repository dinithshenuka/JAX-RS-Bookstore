package lk.ac.iit.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lk.ac.iit.bookstore.exception.notfound.CustomerNotFoundException;
import lk.ac.iit.bookstore.exception.validation.InvalidInputException;
import lk.ac.iit.bookstore.model.Customer;
import lk.ac.iit.bookstore.repository.CustomerRepository;

import java.net.URI;
import java.util.List;

// REST resource for Customer operations
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    private final CustomerRepository customerRepository = CustomerRepository.getInstance();
    
    @Context
    private UriInfo uriInfo;
    
    // Create a new customer
    @POST
    public Response createCustomer(Customer customer) {
        // Validate customer data
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Customer email cannot be empty");
        }
        
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Customer password cannot be empty");
        }
        
        // Check if email is already used
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new InvalidInputException("Email already in use: " + customer.getEmail());
        }
        
        Customer createdCustomer = customerRepository.createCustomer(customer);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdCustomer.getId())).build();
        return Response.created(uri).entity(createdCustomer).build();
    }
    
    // Get all customers
    @GET
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
    
    // Get customer by ID
    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }
    
    // Update a customer
    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") Long id, Customer customer) {
        // Check if the customer exists
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        
        // Validate customer data
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Customer email cannot be empty");
        }
        
        // Check if email is already used by another customer
        Customer existingCustomer = customerRepository.getCustomerById(id);
        if (!existingCustomer.getEmail().equals(customer.getEmail()) && 
            customerRepository.existsByEmail(customer.getEmail())) {
            throw new InvalidInputException("Email already in use: " + customer.getEmail());
        }
        
        Customer updatedCustomer = customerRepository.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            throw new CustomerNotFoundException(id);
        }
        return updatedCustomer;
    }
    
    // Delete a customer
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        boolean deleted = customerRepository.deleteCustomer(id);
        if (!deleted) {
            throw new CustomerNotFoundException(id);
        }
        return Response.noContent().build();
    }
}