package lk.ac.iit.bookstore.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

// Maps CustomerNotFoundException to HTTP 404 response
@Provider
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
    
    @Override
    public Response toResponse(CustomerNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Customer Not Found", exception.getMessage());
        
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}