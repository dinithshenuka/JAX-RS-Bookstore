package lk.ac.iit.bookstore;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import lk.ac.iit.bookstore.exception.mapper.*;
import lk.ac.iit.bookstore.resource.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class BookstoreApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        // Resources
        classes.add(RootResource.class);
        classes.add(BookResource.class);
        classes.add(AuthorResource.class);
        classes.add(CustomerResource.class);
        classes.add(CartResource.class);
        classes.add(OrderResource.class);
        
        // Exception mappers
        classes.add(BookNotFoundExceptionMapper.class);
        classes.add(AuthorNotFoundExceptionMapper.class);
        classes.add(CustomerNotFoundExceptionMapper.class);
        classes.add(CartNotFoundExceptionMapper.class);
        classes.add(OrderNotFoundExceptionMapper.class);
        classes.add(InvalidInputExceptionMapper.class);
        classes.add(OutOfStockExceptionMapper.class);
        classes.add(GlobalExceptionMapper.class);
        classes.add(JsonParseExceptionMapper.class);
        classes.add(JsonbExceptionMapper.class);
        
        return classes;
    }
}