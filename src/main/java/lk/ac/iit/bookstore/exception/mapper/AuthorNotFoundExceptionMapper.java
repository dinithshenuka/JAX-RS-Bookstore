package lk.ac.iit.bookstore.exception.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lk.ac.iit.bookstore.exception.model.ErrorResponse;
import lk.ac.iit.bookstore.exception.notfound.AuthorNotFoundException;

// Maps AuthorNotFoundException to HTTP 404 response
@Provider
public class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException> {
    
    @Override
    public Response toResponse(AuthorNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Author Not Found", exception.getMessage());
        
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}