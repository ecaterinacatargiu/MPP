package main.bookstore.domain.xml_domain;

import main.bookstore.domain.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class XMLTransaction extends Transaction
{
    public static Transaction createFromXMLElement(Element xml)
    {
        Date year;
        try
        {
            year = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH).parse(xml.getElementsByTagName("transactionDate").item(0).getTextContent());
        }
        catch(ParseException psx)
        {
            psx.printStackTrace();
            year = new Date();
        }

        Transaction newBook = new Transaction(
                Long.parseLong(xml.getElementsByTagName("clientID").item(0).getTextContent()),
                Long.parseLong(xml.getElementsByTagName("bookID").item(0).getTextContent()),
                year,
                Integer.parseInt(xml.getElementsByTagName("price").item(0).getTextContent())
        );
        newBook.setID(Long.parseLong(xml.getAttribute("id")));
        return newBook;
    }

    public static Element getXMLElement(Document doc, Transaction trans)
    {
        Element newTrans = doc.createElement("transaction");
        newTrans.setAttribute("id", trans.getID().toString());

        Element clientID = doc.createElement("clientID");
        clientID.appendChild(doc.createTextNode(Long.toString(trans.getClientID())));
        newTrans.appendChild(clientID);

        Element bookID = doc.createElement("bookID");
        bookID.appendChild(doc.createTextNode(Long.toString(trans.getBookID())));
        newTrans.appendChild(bookID);

        Element transDate = doc.createElement("transactionDate");
        transDate.appendChild(doc.createTextNode(trans.getTransactionDate().toString()));
        newTrans.appendChild(transDate);

        return newTrans;
    }
}
