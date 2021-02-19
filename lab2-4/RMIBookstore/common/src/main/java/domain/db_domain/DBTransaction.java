package domain.db_domain;

import domain.Book;
import domain.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DBTransaction extends Transaction {

    public ArrayList<Transaction> getObjectFromDB(Statement statement) throws SQLException {

        ArrayList<Transaction> transactions = null;

        String sql = "select * from Transaction";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            long ttid = resultSet.getLong("ttid");
            long bID = resultSet.getLong("bID");
            long cID = resultSet.getLong("cID");
            Date date = resultSet.getDate("date");
            int price = resultSet.getInt("price");

            Transaction newTransaction = new Transaction(cID, bID, date, price);
            newTransaction.setID(ttid);

            transactions.add(newTransaction);
        }

        resultSet.close();
        return transactions;
    }

    public String getInsertString()
    {
        return "insert into Transaction (ttid, bID, cID, date, price) values("+this.getID()+", "+this.getBookID()+", "+this.getClientID()+", "+this.getTransactionDate()+", "+this.getPrice()+")";
    }

    public String getDeleteString()
    {
        return "delete from Transaction where ttid="+this.getID();
    }

    public String getUpdateString()
    {
        return "update Transaction set bID=?, cID=?, date=?, price=? where ttid="+this.getID();
    }

    public String getSelectString()
    {
        return "select * from Transaction where ttid="+this.getID();
    }
}
