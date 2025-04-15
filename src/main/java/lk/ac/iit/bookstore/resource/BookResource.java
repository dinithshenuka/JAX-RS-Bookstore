package lk.ac.iit.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lk.ac.iit.bookstore.exception.AuthorNotFoundException;
import lk.ac.iit.bookstore.exception.BookNotFoundException;
import lk.ac.iit.bookstore.model.Book;
import lk.ac.iit.bookstore.repository.AuthorRepository;
import lk.ac.iit.bookstore.repository.BookRepository;

import java.net.URI;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    
    private final BookRepository bookRepository = BookRepository.getInstance();
    private final AuthorRepository authorRepository = AuthorRepository.getInstance();
    
    @Context
    private UriInfo uriInfo;
    
    // Create a new book
    @POST
    public Response createBook(Book book) {
        
        // Validate author exists
        if (book.getAuthorId() != null && !authorRepository.existsById(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        
        Book createdBook = bookRepository.createBook(book);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(createdBook.getId())).build();
        return Response.created(uri).entity(createdBook).build();
    }
    
    // Get all books
    @GET
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
    
    // Get books by ID
    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") Long id) {
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }
    
    // update a book
    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
       
        // Check book exists
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        
        // Validate author exists
        if (book.getAuthorId() != null && !authorRepository.existsById(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        
        Book updatedBook = bookRepository.updateBook(id, book);
        if (updatedBook == null) {
            throw new BookNotFoundException(id);
        }
        return updatedBook;
    }
    
    // Delete a book
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        boolean deleted = bookRepository.deleteBook(id);
        if (!deleted) {
            throw new BookNotFoundException(id);
        }
        return Response.noContent().build();
    }
}