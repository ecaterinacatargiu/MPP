package serverside.service;

import domain.Book;
import domain.validators.BookValidator;
import domain.validators.Validator;
import interfaces.BookServiceInterface;
import jdk.jfr.internal.Repository;
import repository.InMemoryRepository;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Optional;

public class BookServiceImpl implements BookServiceInterface {

    private InMemoryRepository<Long, Book> bookRepository;

    public BookServiceImpl()
    {
        Validator<Book> v = new BookValidator();
        this.bookRepository = new InMemoryRepository<Long, Book>(v);
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) throws RemoteException{
        bookRepository.update(book);
    }

    @Override
    public void deleteBook(Long bookID) throws RemoteException{
        bookRepository.delete(bookID);
    }

    @Override
    public Optional<Book> getOneBook(Long bookId)throws RemoteException {
        return bookRepository.findOne(bookId);
    }

    @Override
    public Boolean checkBook(Long bookId) throws RemoteException{
        return bookRepository.findOne(bookId).isPresent();
    }

    @Override
    public Iterable<Book> getAllBooks()throws RemoteException {
        return bookRepository.findAll();
    }
}
