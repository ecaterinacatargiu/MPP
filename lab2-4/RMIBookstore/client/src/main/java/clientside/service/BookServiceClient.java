package clientside.service;

import domain.Book;
import interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.Optional;


public class BookServiceClient implements BookServiceInterface {

    @Autowired
    private BookServiceInterface bookService;

    @Override
    public void addBook(Book book) throws RemoteException
    {
        bookService.addBook(book);
    }

    @Override
    public void updateBook(Book book) throws RemoteException
    {
        bookService.updateBook(book);
    }

    @Override
    public void deleteBook(Long bookID) throws RemoteException
    {
        bookService.deleteBook(bookID);
    }

    @Override
    public Optional<Book> getOneBook(Long bookId)throws RemoteException {
        return bookService.getOneBook(bookId);
    }

    @Override
    public Boolean checkBook(Long bookId)throws RemoteException {
        return bookService.checkBook(bookId);
    }

    @Override
    public Iterable<Book> getAllBooks()throws RemoteException {
        return bookService.getAllBooks();
    }
}
