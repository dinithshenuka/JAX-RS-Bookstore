package lk.ac.iit.bookstore.exception.mapper;

import javax.json.bind.JsonbException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lk.ac.iit.bookstore.exception.model.ErrorResponse;

// Maps JSON parsing exceptions to HTTP 400 Bad Request response
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<ProcessingException> {
    
    @Override
    public Response toResponse(ProcessingException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "Invalid JSON Format", 
            "The request contains invalid or malformed JSON: " + exception.getMessage()
        );
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}