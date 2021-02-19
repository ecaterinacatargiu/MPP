package interfaces;

import domain.Book;
import domain.Client;
import domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;

@Component
public interface BookPublisherInterface extends Remote {

    //book methods
    public void addBook(long bid,String auth,int year) throws RemoteException;
    public void updateBook(long bid,String auth, int y) throws RemoteException;
    public void deleteBook(long bID) throws RemoteException;
    public Book getOneBook(long bID) throws RemoteException;
    public boolean checkBook(Long bid) throws RemoteException;
    public Set<Book> getAllBooks() throws RemoteException;

    //client methods
    public void addClient(long cnp, Date birth, String gen) throws RemoteException;
    public void updateClient(long cnp,Date dateOfBirth,String gender) throws RemoteException;
    public void deleteClient(long cID) throws RemoteException;
    public Client getOneClient(long cID) throws RemoteException;
    public boolean checkClient(Long cnp) throws RemoteException;
    public Set<Client> getAllClients() throws RemoteException;

    //transaction
    public void makeTransaction(Long clientID, Long bookId, int price) throws RemoteException;
    public Transaction getTransactionByTID(Long tid) throws RemoteException;
    public Set<Transaction> getAllTransactions() throws RemoteException;



}
