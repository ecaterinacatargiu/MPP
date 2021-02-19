package ro.ubb.catalog.core.repository;
import org.springframework.stereotype.Repository;
import ro.ubb.catalog.core.model.Book;

public interface BookRepository extends BookPublisherRepository<Book, Long>  {
}

