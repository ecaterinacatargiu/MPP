package tests.bookstore.domain.validators;


import main.bookstore.domain.Book;
import main.bookstore.domain.validators.BookValidator;
import main.exceptions.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestBookValidator
{
    private ArrayList<Book> goodBookList;
    private ArrayList<Book> badBookList;
    private static final long badID = 123456789L;
    private static final long goodID = 1234567891L;
    private static final int goodYear = 2019;
    private static final int badYear = 2143;
    private static final String goodAuthor = "Breje";
    private static final String badAuthor = null;

    private Book book;
    private BookValidator validator;

    public TestBookValidator()
    {
        this.validator = new BookValidator();
    }

    @Before
    public void setUp() throws Exception
    {
        this.book = new Book();
        /*Book b5 = new Book(7768914432L, "Tulai", 2024);
        Book b6 = new Book(776891443L, "Haihai", 2019);

        Book b2 = new Book(1234567891L, "Breje", 1952);
        Book b3 = new Book(9876543219L, "Vai", 43);
        Book b4 = new Book(1829138943L, "Nem", 1982);
        this.goodBookList.add(b2);
        this.goodBookList.add(b3);
        this.goodBookList.add(b4);

        this.badBookList.add(b1);
        this.badBookList.add(b5);
        this.badBookList.add(b6);*/
    }


    @After
    public void tearDown() throws Exception
    {
        this.book = null;
    }

    @Test
    public void testValidateGoodBook() throws Exception
    {
        this.book.setBID(goodID);
        this.book.setAuthor(goodAuthor);
        this.book.setYear(goodYear);
        this.book.setID(goodID+1);

        try
        {
            this.validator.validate(this.book);
            System.out.println("Successful validation of good book");
        }
        catch(ValidatorException valEx)
        {
            tearDown();
            throw new Exception("Bad validator");
        }
        tearDown();
    }

    @Test
    public void testValidateBadBook() throws Exception
    {
        this.book = new Book();
        this.book.setBID(badID);
        this.book.setAuthor(badAuthor);
        this.book.setYear(badYear);
        this.book.setID(badID+1);

        Book b1 = new Book(goodID, badAuthor, goodYear);
        b1.setID(goodID+2);
        Book b2 = new Book(goodID, goodAuthor, badYear);
        b2.setID(goodID -1);
        Book b3 = new Book(badID, goodAuthor, goodYear);
        b3.setID(badID);
        Book b4 = new Book(badID, badAuthor, goodYear);
        b4.setID(badID + 3);
        Book b5 = new Book(badID, goodAuthor, badYear);
        b5.setID(badID -5);

        this.badBookList = new ArrayList<>(100);
        this.badBookList.add(this.book);
        this.badBookList.add(b1);
        this.badBookList.add(b2);
        this.badBookList.add(b3);
        this.badBookList.add(b4);
        this.badBookList.add(b5);

        for(Book bk : this.badBookList)
        {
            try
            {
                this.validator.validate(bk);
            }
            catch(ValidatorException valEx)
            {
                System.out.println("Successful catch of bad book");
            }
        }
        tearDown();
    }



}
