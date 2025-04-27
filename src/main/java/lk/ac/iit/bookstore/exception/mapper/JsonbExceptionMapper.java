package lk.ac.iit.bookstore.exception.mapper;

import javax.json.bind.JsonbException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lk.ac.iit.bookstore.exception.model.ErrorResponse;

// Maps JSONb exceptions to HTTP 400 Bad Request response
@Provider
public class JsonbExceptionMapper implements ExceptionMapper<JsonbException> {
    
    @Override
    public Response toResponse(JsonbException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
            "JSON Binding Error", 
            "Error processing JSON data: " + exception.getMessage()
        );
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}