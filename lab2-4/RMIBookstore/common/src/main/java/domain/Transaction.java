package domain;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction extends BaseEntity<Long> implements Serializable
{
    private long clientID;
    private long bookID;
    private Date transactionDate;
    private int price;

    public Transaction()
    {
        this.clientID = 0;
        this.bookID =0;
        this.transactionDate = null;
        this.price = 0;
    }

    public Transaction(long clientID, long bookID,Date date, int price) {
        this.clientID = clientID;
        this.bookID = bookID;
        this.transactionDate =date;
        this.price = price;
    }

    public int getPrice()
    {
        return this.price;
    }

    public void setPrice(int pr)
    {
        this.price = pr;
    }

    public long getClientID()
    {
        return clientID;
    }

    public long getBookID()
    {
        return bookID;
    }

    public Date getTransactionDate()
    {
        return transactionDate;
    }

    public void setClientID(long clientID)
    {
        this.clientID = clientID;
    }

    public void setBookID(long bookID)
    {
        this.bookID = bookID;
    }

    public void setTransactionDate(Date transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "\nTransaction " + this.getID() + " from date: " + transactionDate + ", client: " +
                clientID + ", book: " + bookID + " bought for: " + this.price + " " + super.toString();
    }
}
