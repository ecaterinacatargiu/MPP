package tests;

import main.bookstore.domain.Client;
import main.bookstore.domain.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    private static final Long transID = 1L;
    private static final Long transID2 = 2L;

    private static final Long clientID = 1234567890123L;
    private static final Long clientID2 = 9876543210987L;

    private static final Long bookID = 1234567890L;
    private static final Long bookID2 = 9876543210L;

    private static final Date transDate = new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime();
    private static final Date transDate2 = new GregorianCalendar(2020, Calendar.MARCH, 23).getTime();

    private static final int aPrice = 100;

    private Transaction transaction = new Transaction(clientID,bookID,transDate, aPrice);

    public TransactionTest(){transaction.setID(transID);}

    @Before
    public void setUp() throws Exception
    {

        transaction = new Transaction(clientID,bookID,transDate, aPrice);
        transaction.setID(transID);
    }

    @After
    public void tearDown() throws Exception
    {
        transaction = null;
    }

    @Test
    public void testGetID()
    {
        assertEquals("trans get id",transaction.getID(),transID);
    }

    @Test
    public void testGetClientID()
    {
        assertEquals("trans get client id",transaction.getClientID(),(Object)clientID);
    }

    @Test
    public void testGerBookID()
    {
        assertEquals("trans get book id",transaction.getBookID(),(Object)bookID);
    }

    @Test
    public void testGetTransDate()
    {
        assertEquals("trans get trans date",transaction.getTransactionDate(),transDate);
    }

    @Test
    public void testSetID()
    {
        transaction.setID(transID2);
        assertEquals("trans set id",transaction.getID(),transID2);
    }

    @Test
    public void testSetClientID()
    {
        transaction.setClientID(clientID2);
        assertEquals("trans set client id",transaction.getClientID(),(Object)clientID2);
    }

    @Test
    public void testSetBookID()
    {
        transaction.setBookID(bookID2);
        assertEquals("set year",transaction.getBookID(),(Object)bookID2);
    }

    @Test
    public void testSetTransDate()
    {
       transaction.setTransactionDate(transDate2);
        assertEquals("set author",transaction.getTransactionDate(),transDate2);
    }
}
