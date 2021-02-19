package serverside.service;

import domain.Book;
import domain.Client;
import domain.Transaction;
import interfaces.BookPublisherInterface;
import repository.InMemoryRepository;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookPublisherImpl implements BookPublisherInterface {

    private BookServiceImpl bookService;
    private ClientServiceImpl clientService;
    private TransactionServiceImpl transactionService;
    private long transactionId = 0;

    public BookPublisherImpl()
    {
        this.bookService = new BookServiceImpl();
        this.clientService = new ClientServiceImpl();
        this.transactionService = new TransactionServiceImpl();
    }

    @Override
    public void addBook(long bid, String auth, int year) throws RemoteException {

        Book book = new Book(bid, auth, year);
        book.setID(bid);
        bookService.addBook(book);
    }

    @Override
    public void updateBook(long bid, String auth, int y) throws RemoteException {

        Book book = new Book(bid, auth, y);
        book.setID(bid);
        bookService.updateBook(book);

    }

    @Override
    public void deleteBook(long bID) throws RemoteException {
        bookService.deleteBook(bID);
    }

    @Override
    public Book getOneBook(long bID) throws RemoteException {

        if (bookService.getOneBook(bID).isPresent())
            return bookService.getOneBook(bID).get();
        else
            throw new RemoteException("Cannot find book with given bid");
    }

    @Override
    public boolean checkBook(Long bid) throws RemoteException {

        return bookService.checkBook(bid);
    }

    @Override
    public Set<Book> getAllBooks() throws RemoteException{

        Iterable<Book> books = bookService.getAllBooks();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void addClient(long cnp, Date birth, String gen) throws RemoteException {

        Client client = new Client(cnp, birth, gen);
        client.setID(cnp);
        clientService.addClient(client);
    }

    @Override
    public void updateClient(long cnp, Date dateOfBirth, String gender) throws RemoteException {

        Client client = new Client(cnp, dateOfBirth, gender);
        client.setID(cnp);
        clientService.addClient(client);

    }

    @Override
    public void deleteClient(long cID) throws RemoteException {

        clientService.deleteClient(cID);
    }

    @Override
    public Client getOneClient(long cID) throws RemoteException {

        if (clientService.getOneClient(cID).isPresent())
            return clientService.getOneClient(cID).get();
        else
            throw new RemoteException("cannot find given client");
    }

    @Override
    public boolean checkClient(Long cnp) throws RemoteException{

        return clientService.checkClient(cnp);
    }

    @Override
    public Set<Client> getAllClients() throws RemoteException{

        Iterable<Client> clients = clientService.getAllClients();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void makeTransaction(Long clientID, Long bookId, int price) throws RemoteException {

        if(checkClient(clientID))
        {
            if(checkBook(bookId))
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date currentDate = new Date();
                Transaction transaction = new Transaction(clientID, bookId, currentDate, price);
                transaction.setID(this.transactionId);
                this.transactionId += 1;

                transactionService.addTransaction(transaction);
            }
            else
            {
                throw new RemoteException("The book is not available");
            }
        }
        else
        {
            throw new RemoteException("The client was not found");
        }

    }

    @Override
    public Transaction getTransactionByTID(Long tid) throws RemoteException {

        if(transactionService.getTransactionById(tid).isPresent())
            return transactionService.getTransactionById(tid).get();
        else
            throw new RemoteException("cannot find given transaction");

    }

    @Override
    public Set<Transaction> getAllTransactions() throws RemoteException {

        Iterable<Transaction> transactions = transactionService.getAllTransactions();
        return StreamSupport.stream(transactions.spliterator(), false).collect(Collectors.toSet());

    }
}
