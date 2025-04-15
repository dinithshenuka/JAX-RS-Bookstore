package lk.ac.iit.bookstore.repository;

import lk.ac.iit.bookstore.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AuthorRepository {
    private static final Map<Long, Author> authors = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(1);
    
    private static AuthorRepository instance;
    
    private AuthorRepository() {
    }
    
    public static synchronized AuthorRepository getInstance() {
        if (instance == null) {
            instance = new AuthorRepository();
        }
        return instance;
    }
    
    // create
    public Author createAuthor(Author author) {
        Long id = idCounter.getAndIncrement();
        author.setId(id);
        authors.put(id, author);
        return author;
    }
    
    // read
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }
    
    // read by id
    public Author getAuthorById(Long id) {
        return authors.get(id);
    }
    
    // update
    public Author updateAuthor(Long id, Author author) {
        if (authors.containsKey(id)) {
            author.setId(id);
            authors.put(id, author);
            return author;
        }
        return null;
    }
    
    // delete
    public boolean deleteAuthor(Long id) {
        return authors.remove(id) != null;
    }
    
    // check existence
    public boolean existsById(Long id) {
        return authors.containsKey(id);
    }
}