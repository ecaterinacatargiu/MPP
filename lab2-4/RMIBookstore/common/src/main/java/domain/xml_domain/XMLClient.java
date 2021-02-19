package domain.xml_domain;

import domain.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class XMLClient extends Client
{

    public static Element getXMLElement(Document doc, Client client)
    {
        Element newClient = doc.createElement("client");
        newClient.setAttribute("id", client.getID().toString());

        Element CNP = doc.createElement("cnp");
        CNP.appendChild(doc.createTextNode(Long.toString(client.getCNP())));
        newClient.appendChild(CNP);

        Element yob = doc.createElement("yob");
        yob.appendChild(doc.createTextNode(client.getYob().toString()));
        newClient.appendChild(yob);

        Element gender = doc.createElement("gender");
        gender.appendChild(doc.createTextNode(client.getGender()));
        newClient.appendChild(gender);

        return newClient;
    }

    public static Client createFromXMLElement(Element xml)
    {
        Date year;
        try
        {
            year = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH).parse(xml.getElementsByTagName("yob").item(0).getTextContent());
        }
        catch(ParseException psx)
        {
            psx.printStackTrace();
            year = new Date();
        }

        Client newBook = new Client(
                Long.parseLong(xml.getElementsByTagName("cnp").item(0).getTextContent()),
                year,
                xml.getElementsByTagName("gender").item(0).getTextContent()
        );
        newBook.setID(Long.parseLong(xml.getAttribute("id")));
        return newBook;
    }
}
