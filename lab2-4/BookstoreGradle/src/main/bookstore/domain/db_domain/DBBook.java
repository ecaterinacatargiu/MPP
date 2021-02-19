package main.bookstore.domain.db_domain;

import main.bookstore.domain.Book;

import java.sql.*;
import java.util.ArrayList;

public class DBBook extends Book
{

    public ArrayList<Book> getObjectFromDB(Statement statement) throws SQLException {

        ArrayList<Book> books = null;

        String sql = "select * from Book";
        ResultSet resultSet = statement.executeQuery(sql);

        //now we need to extract the data from the result set and to put in into an array list
        while(resultSet.next())
        {
            //retrieve each column by name
            long bbookID = resultSet.getLong("bbokID");
            String author = resultSet.getString("author");
            long bookID = resultSet.getLong("bookID");
            int year = resultSet.getInt("year");

            Book newBook = new Book(bookID,author,year);
            newBook.setID(bbookID);

            books.add(newBook);
        }
        resultSet.close();
        return books;
    }

    public String getInsertString()
    {
        return "insert into Book (bbookID, author, bookID, year) values ("+this.getID()+", "+this.getAuthor()+", "+this.getBID()+", "+this.getYear()+")";
    }

    public String getDeleteString()
    {
        return "delete from Book where bbookID="+this.getID();
    }

    public String getUpdateString()
    {
        return "update Book set author=?, bookID=?, year=? where bbookID="+this.getID();
    }

    public String getSelectString()
    {
        return "select * from Book where bbookID="+this.getID();
    }


}
