package domain.xml_domain;

import domain.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLBook extends Book
{
    public static Element getXMLElement(Document doc, Book bk)
    {
        Element newBook = doc.createElement("book");
        newBook.setIdAttribute(bk.getID().toString(), true);

        Element BID = doc.createElement("bid");
        BID.appendChild(doc.createTextNode(Long.toString(bk.getBID())));
        newBook.appendChild(BID);

        Element author = doc.createElement("author");
        author.appendChild(doc.createTextNode(bk.getAuthor()));
        newBook.appendChild(author);

        Element year = doc.createElement("year");
        year.appendChild(doc.createTextNode(Integer.toString(bk.getYear())));
        newBook.appendChild(year);

        return newBook;
    }

    public Book createFromXMLElement(Element xml)
    {
        Book newBook = new Book(
                Long.parseLong(xml.getElementsByTagName("bid").item(0).getTextContent()),
                xml.getElementsByTagName("author").item(0).getTextContent(),
                Integer.parseInt(xml.getElementsByTagName("year").item(0).getTextContent())
        );
        newBook.setID(Long.parseLong(xml.getAttribute("id")));
        return newBook;
    }
}
