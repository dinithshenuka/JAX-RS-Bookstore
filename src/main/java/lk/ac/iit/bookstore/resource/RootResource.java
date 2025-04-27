package lk.ac.iit.bookstore.resource;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//Resource to handle requests to the root path of the API
 @Path("/")
public class RootResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoot() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to the JAX-RS Bookstore API");
        response.put("version", "1.0");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("authors", "/authors - Manage authors");
        endpoints.put("books", "/books - Browse and manage book inventory");
        endpoints.put("customers", "/customers - Customer management");
        endpoints.put("cart", "/customers/{customerId}/cart - Shopping cart operations");
        endpoints.put("orders", "/customers/{customerId}/orders - Order processing and history");
        
        response.put("available_endpoints", endpoints);
        
        return Response.ok(response).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getHtmlRoot() {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>JAX-RS Bookstore API</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            line-height: 1.6;\n" +
                "            max-width: 800px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color: #0066cc;\n" +
                "            border-bottom: 2px solid #0066cc;\n" +
                "            padding-bottom: 10px;\n" +
                "        }\n" +
                "        h2 {\n" +
                "            color: #0099cc;\n" +
                "            margin-top: 30px;\n" +
                "        }\n" +
                "        .endpoint {\n" +
                "            background: #f8f9fa;\n" +
                "            padding: 15px;\n" +
                "            margin-bottom: 15px;\n" +
                "            border-radius: 5px;\n" +
                "            border-left: 5px solid #0099cc;\n" +
                "        }\n" +
                "        .endpoint h3 {\n" +
                "            margin-top: 0;\n" +
                "            color: #0066cc;\n" +
                "        }\n" +
                "        .method {\n" +
                "            display: inline-block;\n" +
                "            padding: 3px 8px;\n" +
                "            border-radius: 4px;\n" +
                "            font-size: 12px;\n" +
                "            font-weight: bold;\n" +
                "            margin-right: 5px;\n" +
                "        }\n" +
                "        .get {\n" +
                "            background: #61affe;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        .post {\n" +
                "            background: #49cc90;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        .put {\n" +
                "            background: #fca130;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        .delete {\n" +
                "            background: #f93e3e;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        footer {\n" +
                "            margin-top: 40px;\n" +
                "            border-top: 1px solid #eee;\n" +
                "            padding-top: 20px;\n" +
                "            font-size: 0.9em;\n" +
                "            color: #666;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Welcome to JAX-RS Bookstore API</h1>\n" +
                "    <p>This is a RESTful API for a bookstore application built with JAX-RS </p>\n" +
                "    \n" +
                "    <h2>Available Endpoints</h2>\n" +
                "    \n" +
                "    <div class=\"endpoint\">\n" +
                "        <h3>/authors</h3>\n" +
                "        <p><span class=\"method get\">GET</span> Get all authors</p>\n" +
                "        <p><span class=\"method post\">POST</span> Create a new author</p>\n" +
                "        <p><span class=\"method get\">GET</span> /authors/{id} - Get author by ID</p>\n" +
                "        <p><span class=\"method put\">PUT</span> /authors/{id} - Update an author</p>\n" +
                "        <p><span class=\"method delete\">DELETE</span> /authors/{id} - Delete an author</p>\n" +
                "        <p><span class=\"method get\">GET</span> /authors/{id}/books - Get books by author</p>\n" +
                "    </div>\n" +
                "    \n" +
                "    <div class=\"endpoint\">\n" +
                "        <h3>/books</h3>\n" +
                "        <p><span class=\"method get\">GET</span> Get all books</p>\n" +
                "        <p><span class=\"method post\">POST</span> Create a new book</p>\n" +
                "        <p><span class=\"method get\">GET</span> /books/{id} - Get book by ID</p>\n" +
                "        <p><span class=\"method put\">PUT</span> /books/{id} - Update a book</p>\n" +
                "        <p><span class=\"method delete\">DELETE</span> /books/{id} - Delete a book</p>\n" +
                "    </div>\n" +
                "    \n" +
                "    <div class=\"endpoint\">\n" +
                "        <h3>/customers</h3>\n" +
                "        <p><span class=\"method get\">GET</span> Get all customers</p>\n" +
                "        <p><span class=\"method post\">POST</span> Create a new customer</p>\n" +
                "        <p><span class=\"method get\">GET</span> /customers/{id} - Get customer by ID</p>\n" +
                "        <p><span class=\"method put\">PUT</span> /customers/{id} - Update a customer</p>\n" +
                "        <p><span class=\"method delete\">DELETE</span> /customers/{id} - Delete a customer</p>\n" +
                "    </div>\n" +
                "    \n" +
                "    <div class=\"endpoint\">\n" +
                "        <h3>/customers/{customerId}/cart</h3>\n" +
                "        <p><span class=\"method get\">GET</span> Get customer's cart</p>\n" +
                "        <p><span class=\"method post\">POST</span> /items - Add item to cart</p>\n" +
                "        <p><span class=\"method put\">PUT</span> /items/{bookId} - Update cart item</p>\n" +
                "        <p><span class=\"method delete\">DELETE</span> /items/{bookId} - Remove cart item</p>\n" +
                "    </div>\n" +
                "    \n" +
                "    <div class=\"endpoint\">\n" +
                "        <h3>/customers/{customerId}/orders</h3>\n" +
                "        <p><span class=\"method post\">POST</span> Create a new order from cart</p>\n" +
                "        <p><span class=\"method get\">GET</span> Get all customer orders</p>\n" +
                "        <p><span class=\"method get\">GET</span> /{orderId} - Get specific order</p>\n" +
                "    </div>\n" +
                "    \n" +
                "    <footer>\n" +
                "        <p>JAX-RS Bookstore API v1.0 | &copy; 2025 Dinith Perera w2051514</p>\n" +
                "    </footer>\n" +
                "</body>\n" +
                "</html>";
        
        return Response.ok(html).build();
    }
}