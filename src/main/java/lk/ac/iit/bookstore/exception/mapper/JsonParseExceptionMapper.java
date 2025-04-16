package lk.ac.iit.bookstore.exception.mapper;

import jakarta.json.bind.JsonbException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lk.ac.iit.bookstore.exception.model.ErrorResponse;

// Maps JSON parsing exceptions to HTTP 400 Bad Request response
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonbException> {
    
    @Override
    public Response toResponse(JsonbException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid JSON Format", "The request contains invalid JSON: " + exception.getMessage());
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}