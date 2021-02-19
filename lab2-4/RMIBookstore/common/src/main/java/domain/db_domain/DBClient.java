package domain.db_domain;

import domain.Book;
import domain.Client;

import java.sql.*;
import java.util.ArrayList;

public class DBClient extends Client {

    public ArrayList<Client> getObjectFromDB(Statement statement) throws SQLException {

        ArrayList<Client> clients = null;

        String sql = "select * from Client";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next())
        {
            long ccid = resultSet.getLong("ccid");
            long cnp = resultSet.getLong("cnp");
            String gender = resultSet.getString("gender");
            Date yob = resultSet.getDate("yob");

            Client newClient = new Client(cnp,yob,gender);
            newClient.setID(ccid);

            clients.add(newClient);
        }
        resultSet.close();
        return clients;
    }

    public String getInsertString()
    {
        return "insert into Client (ccid,cnp,gender,yob) values("+this.getID()+", "+this.getCNP()+", "+this.getGender()+", "+this.getYob()+")";
    }

    public String getDeleteString()
    {
        return "delete from Client where ccidid="+this.getID();
    }

    public String getUpdateString()
    {
        return "update Client set cnp=?, gender=?, yob=? where ccid="+this.getID();
    }

    public String getSelectString()
    {
        return "select * from Client where ccid="+this.getID();
    }
}
