package interfaces;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

@Component
public interface BookServiceInterface extends Remote {

    public void addBook(Book book) throws RemoteException;
    public void updateBook(Book book) throws RemoteException;
    public void deleteBook(Long bookID) throws RemoteException;

    public Optional<Book>getOneBook(Long bookId) throws RemoteException;
    public Boolean checkBook(Long bookId) throws RemoteException;
    public Iterable<Book> getAllBooks() throws RemoteException;

}
