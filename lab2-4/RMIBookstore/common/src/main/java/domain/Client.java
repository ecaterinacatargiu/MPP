package domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import domain.BaseEntity;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Client extends BaseEntity<Long> implements Serializable
{
    private long CNP;
    private Date yob;
    private String gender;

    public Client()
    {
        this.CNP = 0;
        this.yob = null;
        this.gender = null;
    }

    public Client(long CNP, Date yob, String gender)
    {
        this.CNP = CNP;
        this.yob = yob;
        this.gender = gender;
    }

    //getters
    public long getCNP()
    {
        return this.CNP;
    }

    public Date getYob()
    {
        return this.yob;
    }

    public String getGender()
    {
        return this.gender;
    }

    //setters
    public void setCNP(long newCNP)
    {
        this.CNP = newCNP;
    }

    public void setYob(Date newYob)
    {
        this.yob = newYob;
    }

    public void setGender(String newGender)
    {
        this.gender = newGender;
    }

    //verify
    public boolean equals(Object obj)
    {
        if( obj instanceof Client)
            return ((Client) obj).getCNP() == CNP && ((Client) obj).getYob() == yob && ((Client) obj).getGender().equals(gender);
            return false;
    }

    //toString
    public String toString()
    {
        return "Client { " + "ID: " +  String.valueOf(CNP) + " Year of birth: " + yob.toString() + " Gender: " + gender + " } " + super.toString();
    }

}
