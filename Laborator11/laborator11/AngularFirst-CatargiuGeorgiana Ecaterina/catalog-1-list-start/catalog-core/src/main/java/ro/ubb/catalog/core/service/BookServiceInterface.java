package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookServiceInterface
{
    public Book addBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Long bookID);
    public Optional<Book> getOneBook(Long bookID);
    public boolean checkBook(Long bid);
    public List<Book> getAllBooks();
}
