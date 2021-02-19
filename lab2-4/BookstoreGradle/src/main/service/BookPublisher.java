package main.service;
import main.bookstore.domain.Book;
import main.bookstore.domain.Client;
import main.bookstore.domain.Transaction;
import main.bookstore.domain.validators.BookValidator;
import main.bookstore.domain.validators.ClientValidator;
import main.bookstore.domain.validators.TransactionValidator;
import main.bookstore.domain.validators.Validator;
import main.repository.InMemoryRepository;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import main.repository.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;



public class BookPublisher extends main.bookstore.domain.BaseEntity<Long> implements Serializable {

    private long BPID;
    private Date dateTransaction;
    private BookService bookService;
    private ClientService clientService;
    private TransactionService transactionService;
    private long transactionId = 0;

    public BookPublisher()
    {
        this.BPID = 0;
        this.dateTransaction = null;
        Validator<Client> clientValidator = new ClientValidator();
        this.clientService = new ClientService();
        Validator<Book> bookValidator = new BookValidator();
        this.bookService = new BookService();
        Validator<Transaction> transactionValidator = new TransactionValidator();
        this.transactionService = new TransactionService();
        this.transactionId =0;
    }

    public BookPublisher(long ID, Date dateTransaction, ClientService c, BookService b, TransactionService t, Long transactionId)
    {
        this.BPID = ID;
        this.dateTransaction = dateTransaction;
        this.clientService = c;
        this.bookService = b;
        this.transactionService = t;
        this.transactionId = 0;
    }



    //toString
    public String toString()
    {
        return "BookPublisher { " + "ID: " + String.valueOf(BPID) + "Date of transaction: " +
                dateTransaction.toString() + "Client: " + clientService.toString() + "Book: " +
                bookService.toString() + "Transaction: " + transactionService.toString();
    }

    //verify
    public boolean equals(Object obj)
    {
        if(obj instanceof BookPublisher)
        {
            return ((BookPublisher)obj).getBPID() == BPID &&
                    ((BookPublisher) obj).getDateTransaction() == dateTransaction &&
                    ((BookPublisher) obj).getBookService().equals(bookService) &&
                    ((BookPublisher) obj).getClientService().equals(clientService) &&
                    ((BookPublisher)obj).getTransactionService().equals(transactionService) &&
                    ((BookPublisher)obj).getTransactionId()== transactionId;
        }
        return false;
    }


    //book methods
    public void addBook(long bid,String auth,int year) throws Exception
    {
        Book book = new Book(bid,auth,year);
        book.setID(bid);
        bookService.addBook(book);
    }

    public void updateBook(long bid,String auth, int y) throws Exception
    {
        Book book = new Book(bid,auth,y);
        book.setID(bid);
        bookService.updateBook(book);
    }

    public void deleteBook(long bID) throws SQLException {
        bookService.deleteBook(bID);
    }

    public Book getOneBook(long bID) throws Exception
    {
        if (bookService.getOneBook(bID).isPresent())
            return bookService.getOneBook(bID).get();
        else
            throw new Exception("Cannot find book with given bid");
    }


    public boolean checkBook(Long bid)
    {
        return bookService.checkBook(bid);
    }

    public Set<Book> getAllBooks()
    {
        Iterable<Book> books = bookService.getAllBooks();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }


    //client methods
    public void addClient(long cnp,Date birth,String gen) throws Exception
    {
        Client client = new Client(cnp,birth,gen);
        client.setID(cnp);
        clientService.addClient(client);
    }

    public void updateClient(long cnp,Date dateOfBirth,String gender) throws Exception
    {
        Client client = new Client(cnp,dateOfBirth,gender);
        client.setID(cnp);
        clientService.updateClient(client);
    }

    public void deleteClient(long cID) throws SQLException {
        clientService.deleteClient(cID);
    }

    public Client getOneClient(long cID) throws Exception
    {
        if (clientService.getOneClient(cID).isPresent())
            return clientService.getOneClient(cID).get();
        else
            throw new Exception("cannot find given client");
    }

    public boolean checkClient(Long cnp)
    {
        return clientService.checkClient(cnp);
    }

    public Set<Client> getAllClients()
    {
        Iterable<Client> clients = clientService.getAllClients();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }


    //transaction methods


    public void makeTransaction(Long clientID, Long bookId, int price) throws Exception
    {
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
                throw new Exception("The book is not available");
            }
        }
        else
        {
            throw new Exception("The client was not found");
        }
    }


    public Transaction getTransactionByTID(Long tid) throws Exception
    {

         if(transactionService.getTransactionByTID(tid).isPresent())
             return transactionService.getTransactionByTID(tid).get();
         else
             throw new Exception("cannot find given transaction");

    }

    public boolean checkTransaction(Long tid)
    {
        return transactionService.checkTransactions(tid);
    }

    public Set<Transaction> getAllTransactions()
    {
        Iterable<Transaction> transactions = transactionService.getAllTransactions();
        return StreamSupport.stream(transactions.spliterator(), false).collect(Collectors.toSet());
    }


    //xml files things
    public Element getXMLElement(Document doc)
    {
        Element newBookPublisher = doc.createElement("bookPublisher");
        newBookPublisher.setAttribute("id", this.getID().toString());

        Element newBpid = doc.createElement("bpid");
        newBpid.appendChild(doc.createTextNode(Long.toString(this.BPID)));
        newBookPublisher.appendChild(newBpid);

        Element dateTransaction = doc.createElement("dateTransaction");
        dateTransaction.appendChild(doc.createTextNode(this.dateTransaction.toString()));
        newBookPublisher.appendChild(dateTransaction);

        Element repoClient = doc.createElement("repositoryClients");
        repoClient.appendChild(doc.createTextNode(this.clientService.toString()));
        newBookPublisher.appendChild(repoClient);

        Element repoBook = doc.createElement("repositoryBooks");
        repoBook.appendChild(doc.createTextNode(this.bookService.toString()));
        newBookPublisher.appendChild(repoBook);

        Element repoTrans = doc.createElement("repositoryTransactions");
        repoBook.appendChild(doc.createTextNode(this.transactionService.toString()));
        newBookPublisher.appendChild(repoBook);

        return newBookPublisher;

    }

    public BookPublisher createFromXMLElement(Element xml)
    {
        Date dateTransaction;
        try
        {
            dateTransaction = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH).parse(xml.getElementsByTagName("dateTransaction").item(0).getTextContent());
        }
        catch(ParseException psx)
        {
            psx.printStackTrace();
            dateTransaction = new Date();
        }
        Repository<Long, Client> repoClients = null;
        Repository<Long, Book> repoBooks = null;
        Long transactionId = null;

        BookPublisher newBookPublisher = new BookPublisher(
                Long.parseLong(xml.getElementsByTagName("BPID").item(0).getTextContent()),
                dateTransaction,
                null,
                null,
                null,
                transactionId
        );

        newBookPublisher.setID(Long.parseLong(xml.getAttribute("id")));
        return newBookPublisher;

    }

    public Set<Transaction> reportSellsBefore(Date beforeDate)
    {
        return StreamSupport.stream(this.transactionService.getAllTransactions().spliterator(), false)
                .filter(
                        x ->
                        {
                            return x.getTransactionDate().before(beforeDate);
                        }
                )
                .collect(Collectors.toSet());
    }

    public List<Map.Entry<Client, Long>> reportClientBySpending()
    {
        Iterable<Client> clientsInSystem = this.clientService.getAllClients();
        Iterable<Transaction> allTransactions = this.transactionService.getAllTransactions();

        Map<Client, Long> clientPrice = new HashMap<>();
        clientsInSystem.forEach(x->clientPrice.put(x, 0L));
        allTransactions.forEach(
                x ->
                {
                     Client cl = null;
                     Book bk = null;
                     if (checkClient(x.getClientID()))
                     {
                         cl = (Client) this.clientService.getOneClient(x.getClientID()).get();
                     }
                     Long oldPrice = clientPrice.get(cl);
                     if (checkBook(x.getBookID()))
                     {
                         bk = (Book) this.bookService.getOneBook(x.getBookID()).get();
                     }
                     Long newPrice = oldPrice + x.getPrice();
                     clientPrice.put(cl, newPrice);
                }
            );

        Set<Map.Entry<Client, Long>> set = clientPrice.entrySet();
        List<Map.Entry<Client, Long>> list = new ArrayList<Map.Entry<Client, Long>>(set);
        list.sort(new Comparator<Map.Entry<Client, Long>>() {
            public int compare(Map.Entry<Client, Long> o1, Map.Entry<Client, Long> o2) {
                int result = (o1.getValue()).compareTo(o2.getValue());
                if (result != 0) {
                    return result;
                }
                else {
                    if (o1.getKey().getID() < o2.getKey().getID()) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
            }
        });
        return list;
    }


    public long getBPID() {
        return BPID;
    }

    public void setBPID(long BPID) {
        this.BPID = BPID;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
