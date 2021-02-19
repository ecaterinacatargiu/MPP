package main;

import main.bookstore.domain.Book;
import main.bookstore.domain.Client;
import main.bookstore.domain.Transaction;
import main.service.BookPublisher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class PublisherServiceServer implements PublisherServiceInterface
{
    private BookPublisher bookPublisher;
    private ExecutorService executorService;

    public PublisherServiceServer(ExecutorService execS, BookPublisher bpbl)
    {
        this.executorService = execS;
        this.bookPublisher = bpbl;
    }

    public PublisherServiceServer(ExecutorService execS)
    {
        this.executorService = execS;
        this.bookPublisher = new BookPublisher();
    }

    private ArrayList<String> CSVsplitBody(String str)
    {
        if (null == str)
        {
            return null;
        }
        return new ArrayList<>(Arrays.asList(str.split(",[ ]*")));
    }

    @Override
    public Future<String> deleteBook(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            long bid = Long.parseLong(members.get(0));

            this.bookPublisher.deleteBook(bid);

            return executorService.submit(() -> "The book has been deleted");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The book couldn't be deleted" + ex.getMessage());
        }
    }

    @Override
    public Future<String> addClient(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            SimpleDateFormat format= new SimpleDateFormat("dd-mm-yyyy");

            long CNP = Long.parseLong(members.get(0));
            Date yob = format.parse(members.get(1));
            String gender = members.get(2);

            this.bookPublisher.addClient(CNP, yob, gender);

            return executorService.submit(() -> "The client has been added");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The client couldn't be added" + ex.getMessage());
        }
    }

    @Override
    public Future<String> deleteClient(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            long CNP = Long.parseLong(members.get(0));

            this.bookPublisher.deleteClient(CNP);

            return executorService.submit(() -> "The client has been deleted");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The client couldn't be deleted" + ex.getMessage());
        }
    }

    @Override
    public Future<String> showClients(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            StringBuilder toti = new StringBuilder();

            Iterable<Client> lista = this.bookPublisher.getAllClients();
            for(Client cl : lista)
            {
                toti.append(cl.toString());
                toti.append("\n");
            }

            return executorService.submit(toti::toString);
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The clients couldn't be retrieved" + ex.getMessage());
        }
    }

    @Override
    public Future<String> showBooks(String body)
    {        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            StringBuilder toti = new StringBuilder();

            Iterable<Book> lista = this.bookPublisher.getAllBooks();
            for(Book bk : lista)
            {
                toti.append(bk.toString());
                toti.append(";");
            }

            String da = toti.toString();
            return executorService.submit(() -> da);
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The books couldn't be retrieved" + ex.getMessage());
        }
    }

    @Override
    public Future<String> updateClient(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");

            long CNP = Long.parseLong(members.get(0));
            Date yob = format.parse(members.get(1));
            String gender = members.get(2);

            this.bookPublisher.updateClient(CNP, yob, gender);

            return executorService.submit(() -> "The client has been updated");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The client couldn't be updated" + ex.getMessage());
        }
    }

    @Override
    public Future<String> updateBook(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            long bid = Long.parseLong(members.get(0));
            String author = members.get(1);
            int year = Integer.parseInt(members.get(2));

            this.bookPublisher.updateBook(bid, author, year);

            return executorService.submit(() -> "The book has been updated");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The book couldn't be updated " + ex.getMessage());
        }
    }

    @Override
    public Future<String> checkBook(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            long bid = Long.parseLong(members.get(0));

            if (!this.bookPublisher.checkBook(bid))
            {
                return executorService.submit(() -> "We don't have the given book");
            }
            Book bk = this.bookPublisher.getOneBook(bid);
            return executorService.submit(() -> "Here is your book: " + bk.toString());
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "Couldn't check for book " + ex.getMessage());
        }
    }

    @Override
    public Future<String> checkClient(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            long cnp = Long.parseLong(members.get(0));

            if (!this.bookPublisher.checkClient(cnp))
            {
                return executorService.submit(() -> "We don't have the given client");
            }
            Client cl = this.bookPublisher.getOneClient(cnp);
            return executorService.submit(() -> "Here is the client: " + cl.toString());
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "Couldn't check for client " + ex.getMessage());
        }
    }

    @Override
    public Future<String> makeTransaction(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            long cnp = Long.parseLong(members.get(0));
            long bid = Long.parseLong(members.get(1));
            int price = Integer.parseInt(members.get(2));

            this.bookPublisher.makeTransaction(cnp, bid, price);
            return executorService.submit(() -> "The transaction completed successfully");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The transaction couldn't be made " + ex.getMessage());
        }

    }

    @Override
    public Future<String> showTransactions(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            StringBuilder toti = new StringBuilder();

            Iterable<Transaction> lista = this.bookPublisher.getAllTransactions();
            for(Transaction tr : lista)
            {
                toti.append(tr.toString());
            }

            return executorService.submit(toti::toString);
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The transactions couldn't be retrieved " + ex.getMessage());
        }
    }

    @Override
    public Future<String> clientSpending(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);
        try
        {
            StringBuilder toti = new StringBuilder();

            Iterable<Map.Entry<Client, Long>> lista = this.bookPublisher.reportClientBySpending();
            for(Map.Entry<Client, Long> tr : lista)
            {
                toti.append(tr.toString());
            }

            return executorService.submit(toti::toString);
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The clients couldn't be retrieved " + ex.getMessage());
        }
    }

    @Override
    public Future<String> transactBefore(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);

        try
        {
            SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
            StringBuilder toti = new StringBuilder();

            Date before = format.parse(members.get(0));

            Iterable<Transaction> lista = this.bookPublisher.reportSellsBefore(before);
            for(Transaction tr : lista)
            {
                toti.append(tr.toString());
                toti.append("\n");
            }

            return executorService.submit(toti::toString);
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The transactions couldn't be retrieved" + ex.getMessage());
        }
    }

    @Override
    public Future<String> addBook(String body)
    {
        ArrayList<String> members = CSVsplitBody(body);

        try
        {
            long bid = Long.parseLong(members.get(0));
            String author = members.get(1);
            int year = Integer.parseInt(members.get(2));

            this.bookPublisher.addBook(bid, author, year);
            return executorService.submit(() -> "The book has been added");
        }
        catch(Exception ex)
        {
            return executorService.submit(() -> "The book couldn't be added" + ex.getMessage());
        }

    }
}
