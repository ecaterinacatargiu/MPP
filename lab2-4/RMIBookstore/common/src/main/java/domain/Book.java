package domain;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

@Component
public class Book extends BaseEntity<Long> implements Serializable
{
    private long BID;
    private String author;
    private int year;

    public Book()
    {
        this.BID = 0;
        this.author = null;
        this.year = 0;
    }
    public Book(long BID, String author, int year)
    {
        this.BID = BID;
        this.author = author;
        this.year = year;
    }

    public long getBID()
    {
        return BID;
    }

    public void setBID(long BID)
    {
        this.BID = BID;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    @Override
    public String toString()
    {
        return "Book: " +
                " BID=" + BID +
                ", author=" + author  +
                ", year=" + year;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Book)
            if(((Book) obj).getBID()==BID && ((Book) obj).getAuthor()==author && ((Book) obj).getYear()==year)
                return true;
        return false;
    }
}
