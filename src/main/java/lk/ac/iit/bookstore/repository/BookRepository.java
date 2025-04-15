package lk.ac.iit.bookstore.repository;

import lk.ac.iit.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BookRepository {
    private static final Map<Long, Book> books = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private static BookRepository instance;
    
    private BookRepository() {
    }
    
    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }
    
    // Create a new book
    public Book createBook(Book book) {
        Long id = idCounter.getAndIncrement();
        book.setId(id);
        books.put(id, book);
        return book;
    }
    
    // Get all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    // Get a book by ID
    public Book getBookById(Long id) {
        return books.get(id);
    }
    
    // Update a book
    public Book updateBook(Long id, Book book) {
        if (books.containsKey(id)) {
            book.setId(id);
            books.put(id, book);
            return book;
        }
        return null;
    }
    
    // Delete a book
    public boolean deleteBook(Long id) {
        return books.remove(id) != null;
    }
    
    // Get books by author 
    public List<Book> getBooksByAuthor(Long authorId) {
        return books.values().stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
    
    // Check if a book exists 
    public boolean existsById(Long id) {
        return books.containsKey(id);
    }
}