package main.service;

import main.bookstore.domain.Book;
import main.bookstore.domain.validators.BookValidator;
import main.bookstore.domain.validators.Validator;
import main.repository.InMemoryRepository;
import main.repository.Repository;

import java.sql.SQLException;
import java.util.Optional;

public class BookService
{
    private Repository<Long,Book> bookRepository;

    public BookService()
    {
        Validator<Book> v = new BookValidator();
        this.bookRepository = new InMemoryRepository<>(v);
    }


    public void addBook(Book book) throws Exception
    {
        bookRepository.save(book);
    }

    public void updateBook(Book book) throws Exception
    {
        bookRepository.update(book);
    }

    public void deleteBook(Long bookID) throws SQLException {
        bookRepository.delete(bookID);
    }

    public Optional<Book> getOneBook(Long bookID)
    {
        return bookRepository.findOne(bookID);

    }


    public boolean checkBook(Long bid)
    {
        return bookRepository.findOne(bid).isPresent();
    }

    public Iterable<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

}
