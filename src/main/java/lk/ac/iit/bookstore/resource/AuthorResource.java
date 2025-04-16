package lk.ac.iit.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lk.ac.iit.bookstore.exception.notfound.AuthorNotFoundException;
import lk.ac.iit.bookstore.model.Author;
import lk.ac.iit.bookstore.model.Book;
import lk.ac.iit.bookstore.repository.AuthorRepository;
import lk.ac.iit.bookstore.repository.BookRepository;

import java.net.URI;
import java.util.List;

// REST resource for Author operations
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    
    private final AuthorRepository authorRepository = AuthorRepository.getInstance();
    private final BookRepository bookRepository = BookRepository.getInstance();
    
    @Context
    private UriInfo uriInfo;
    
    // Create a new author
    @POST
    public Response createAuthor(Author author) {
        Author createdAuthor = authorRepository.createAuthor(author);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdAuthor.getId())).build();
        return Response.created(uri).entity(createdAuthor).build();
    }
    
    // Get all authors
    @GET
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }
    
    // Get author by ID
    @GET
    @Path("/{id}")
    public Author getAuthorById(@PathParam("id") Long id) {
        Author author = authorRepository.getAuthorById(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }
        return author;
    }
    
    // Update an author
    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") Long id, Author author) {
        Author updatedAuthor = authorRepository.updateAuthor(id, author);
        if (updatedAuthor == null) {
            throw new AuthorNotFoundException(id);
        }
        return updatedAuthor;
    }
    
    // Delete an author
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") Long id) {
        boolean deleted = authorRepository.deleteAuthor(id);
        if (!deleted) {
            throw new AuthorNotFoundException(id);
        }
        return Response.noContent().build();
    }
    
    // Get books by author
    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        return bookRepository.getBooksByAuthor(id);
    }
}