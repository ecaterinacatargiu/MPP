package tests;

import main.bookstore.domain.Book;
import main.service.BookPublisher;
import main.bookstore.domain.Client;
import main.bookstore.domain.Transaction;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBookPublisher
{

    @Test
    public void testReports()
    {
        try
        {
            BookPublisher bookPublisher = new BookPublisher();
            Client cl = new Client(1922895321923L, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "male");
            cl.setID(1922895321923L);
            Book bk = new Book(1234567891L, "Breje", 2019);
            bk.setID(1234567891L);
            bookPublisher.addBook(1234567891L, "Breje", 2019);
            bookPublisher.addClient(1922895321923L, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "male");
            bookPublisher.makeTransaction(cl.getID(), bk.getID(), 100);
            TimeUnit.SECONDS.sleep(2);

            Transaction theTransaction = bookPublisher.getTransactionByTID(0L);
            assertTrue(bookPublisher.reportSellsBefore(new Date()).contains(theTransaction));

            Client c2 = new Client(1922895321924L, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "male");
            c2.setID(1922895321924L);
            Book bk2 = new Book(1234567892L, "Brejee", 2019);
            bk2.setID(1234567892L);
            bookPublisher.addClient(1922895321924L,new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(),"male");
            bookPublisher.addBook(1234567892L, "Brejee", 2019);

            bookPublisher.makeTransaction(c2.getID(), bk2.getID(), 19);

            bookPublisher.reportClientBySpending().stream().forEach(System.out::println);
            System.out.print("Reports working.");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
