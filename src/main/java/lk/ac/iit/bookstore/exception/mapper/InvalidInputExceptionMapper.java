package lk.ac.iit.bookstore.exception.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lk.ac.iit.bookstore.exception.model.ErrorResponse;
import lk.ac.iit.bookstore.exception.validation.InvalidInputException;

// Maps InvalidInputException to HTTP 400 response
@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    
    @Override
    public Response toResponse(InvalidInputException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Input", exception.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}