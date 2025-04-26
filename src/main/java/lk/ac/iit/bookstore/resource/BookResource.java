package lk.ac.iit.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lk.ac.iit.bookstore.exception.notfound.AuthorNotFoundException;
import lk.ac.iit.bookstore.exception.notfound.BookNotFoundException;
import lk.ac.iit.bookstore.exception.validation.InvalidInputException;
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
        // Validate required fields
        if (book == null) {
            throw new InvalidInputException("Book data cannot be null");
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty");
        }
        
        if (book.getAuthorId() == null) {
            throw new InvalidInputException("Author ID is required");
        }
        
        // Validate author exists
        if (!authorRepository.existsById(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        
        // Validate price and stock
        if (book.getPrice() < 0) {
            throw new InvalidInputException("Book price cannot be negative");
        }
        
        if (book.getStock() < 0) {
            throw new InvalidInputException("Book stock cannot be negative");
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
        // Validate required fields
        if (book == null) {
            throw new InvalidInputException("Book data cannot be null");
        }
        
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty");
        }
        
        if (book.getAuthorId() == null) {
            throw new InvalidInputException("Author ID is required");
        }
        
        // Check book exists
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        
        // Validate author exists
        if (!authorRepository.existsById(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        
        // Validate price and stock
        if (book.getPrice() < 0) {
            throw new InvalidInputException("Book price cannot be negative");
        }
        
        if (book.getStock() < 0) {
            throw new InvalidInputException("Book stock cannot be negative");
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