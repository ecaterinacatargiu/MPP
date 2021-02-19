package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.bookstore.domain.Book;

public class BookTest {
    private static final Long id = 1L;
    private static final Long otherId = 2L;

    private static final Long bid = 1234567890L;
    private static final Long otherbid = 9876543210L;

    private static final String author = "author1";
    private static final String otherAuthor = "author2";

    private static final int year = 2020;
    private static final int otherYear = 2008;


    private Book book=new Book(bid,author,year);


    public BookTest(){book.setID(id);}

    @Before
    public void setUp() throws Exception
    {

        book = new Book(bid,author,year);
        book.setID(id);
    }

    @After
    public void tearDown() throws Exception
    {
        book = null;
    }

    //test getters

    @Test
    public void testGetID()
    {
        assertEquals("book get id",id,book.getID());
    }

    @Test
    public void testGetBID()
    {
        assertEquals("get bid",(Object) bid,book.getBID());
    }

    @Test
    public void testGetAuthor()
    {
        assertEquals("get author",author,book.getAuthor());
    }

    @Test
    public void testGetYear()
    {
        assertEquals( "get year",year,book.getYear());
    }

    //test setters
    @Test
    public void testSetID()
    {
        book.setID(otherId);
        assertEquals("book set id",(Object)otherId,book.getID());
    }

    @Test
    public void testSetBID()
    {
        book.setBID(otherbid);
        assertEquals("set bid",(Object) otherbid,book.getBID());
    }

    @Test
    public void testSetYear()
    {
        book.setYear(otherYear);
        assertEquals("set year",otherYear,book.getYear());
    }

    @Test
    public void testSetAuthor()
    {
        book.setAuthor(otherAuthor);
        assertEquals("set author",otherAuthor,book.getAuthor());
    }
}
