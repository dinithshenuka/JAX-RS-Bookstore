package lk.ac.iit.bookstore.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lk.ac.iit.bookstore.exception.model.ErrorResponse;
import lk.ac.iit.bookstore.exception.notfound.BookNotFoundException;

// Maps BookNotFoundException to HTTP 404 response
@Provider
public class BookNotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException> {
    
    @Override
    public Response toResponse(BookNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Book Not Found", exception.getMessage());
        
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}