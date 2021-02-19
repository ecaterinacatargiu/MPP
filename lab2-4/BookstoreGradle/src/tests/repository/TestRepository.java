package tests.repository;

import main.bookstore.domain.Book;
import main.bookstore.domain.Client;
import main.bookstore.domain.validators.ClientValidator;
import main.bookstore.domain.xml_domain.XMLClient;
import main.repository.FileRepository;
import main.repository.Repository;
import main.repository.InMemoryRepository;
import main.repository.XMLRepository;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class TestRepository
{

    private static final Long goodCNP = 1922895321923L;
    private static final Long badCNP =  192289532192L;
    private static final String goodGender = "male";
    private static final String badGender = "pisica";
    private static final Date goodDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private static final Date badDate1 = new GregorianCalendar(2091, Calendar.MARCH, 23).getTime();
    private static final Date badDate2 = new GregorianCalendar(1842, Calendar.JULY, 9).getTime();


    @Test
    public void removeFromXMLRepository() throws Exception
    {
        ClientValidator clientValidator = new ClientValidator();
        Path currentTestFile = Paths.get(".\\BookstoreGradle\\src\\tests\\repository\\testRemove.xml");
        XMLRepository<Long, Client> textClientRepository = new XMLRepository<>(clientValidator, currentTestFile.toAbsolutePath().toString(), "client", (XMLClient::getXMLElement), (XMLClient::createFromXMLElement));

        textClientRepository.findAll().forEach(System.out::println);

        textClientRepository.delete(goodCNP);
        System.out.print("Yet again\n");

        textClientRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testLoadXMLRepository() throws Exception
    {
        ClientValidator clientValidator = new ClientValidator();
        Path currentTestFile = Paths.get(".\\BookstoreGradle\\src\\tests\\repository\\teste.xml");
        XMLRepository<Long, Client> textClientRepository = new XMLRepository<>(clientValidator, currentTestFile.toAbsolutePath().toString(), "client", (XMLClient::getXMLElement), (XMLClient::createFromXMLElement));

        textClientRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testXMLInitializationRepository() throws Exception
    {
        ClientValidator clientValidator = new ClientValidator();
        Path currentTestFile = Paths.get(".\\BookstoreGradle\\src\\tests\\repository\\teste.xml");
        XMLRepository<Long, Client> textClientRepository = new XMLRepository<>(clientValidator, currentTestFile.toAbsolutePath().toString(), "client", (XMLClient::getXMLElement), (XMLClient::createFromXMLElement));

        Client cl1 = new Client(goodCNP, goodDate, goodGender);
        cl1.setID(goodCNP);
        Client cl2 = new Client(goodCNP, goodDate, goodGender);
        cl2.setID(goodCNP+1);
        Client cl3 = new Client(goodCNP, goodDate, goodGender);
        cl3.setID(goodCNP+2);

        textClientRepository.save(cl1);
        textClientRepository.save(cl2);
        textClientRepository.save(cl3);

    }


    @Test
    public void testFileCreateRepository() throws Exception
    {
        ClientValidator clientValidator = new ClientValidator();
        Path currentTestFile = Paths.get(".\\BookstoreGradle\\src\\tests\\repository\\teste.txt");
        Repository<Long, Client> textClientRepository = new FileRepository<>(clientValidator, currentTestFile.toAbsolutePath().toString());

        try
        {
            Client cl1 = new Client(goodCNP, goodDate, goodGender);
            cl1.setID(goodCNP);
            Client cl2 = new Client(goodCNP, goodDate, goodGender);
            cl2.setID(goodCNP+1);
            Client cl3 = new Client(goodCNP, goodDate, goodGender);
            cl3.setID(goodCNP+2);

            textClientRepository.save(cl1);
            textClientRepository.save(cl2);
            textClientRepository.save(cl3);

            Repository<Long,Client> secondTextClientRepository = new FileRepository<>(clientValidator, currentTestFile.toAbsolutePath().toString());
            secondTextClientRepository.findAll().forEach(System.out::println);

            assertEquals(secondTextClientRepository.findOne(goodCNP).toString(), Optional.of(cl1).toString());

            Client cl4 = new Client(goodCNP, goodDate, goodGender);
            cl4.setID(goodCNP+4);

            secondTextClientRepository.save(cl4);

            System.out.println("\nA new entity can be added\n");
            secondTextClientRepository.findAll().forEach(System.out::println);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void testBadRepository() throws Exception
    {
        ClientValidator clientValidator = new ClientValidator();
        Repository<Long, Client> badClientRepository = new InMemoryRepository<Long, Client>(clientValidator);

        Client badClient = new Client(badCNP, badDate2, badGender);
        badClient.setID(badCNP+2);

        Client goodClient = new Client(goodCNP, goodDate, goodGender);
        goodClient.setID(badCNP+2);
        Client sameClient = new Client(goodCNP, goodDate, goodGender);
        goodClient.setID(badCNP+2);

        try
        {
            badClientRepository.save(badClient);
            throw new Exception("Repository doesn't catch invalid client");
        }
        catch(Exception exc)
        {
            System.out.println("Repository catches bad client adding");
        }

        try
        {
            badClientRepository.save(goodClient);
            badClientRepository.save(sameClient);
            throw new Exception("Repository doesn't catch invalid client");
        }
        catch(Exception exc)
        {
            System.out.println("Repository catches bad client adding");
        }
    }

    @Test
    public void testGoodRepository() throws Exception
    {
        ClientValidator clientValidator = new ClientValidator();
        Repository<Long, Client> goodClientRepository = new InMemoryRepository<Long, Client>(clientValidator);

        Client cl1 = new Client(goodCNP, goodDate, goodGender);
        cl1.setID(goodCNP);
        Client cl2 = new Client(goodCNP, goodDate, goodGender);
        cl2.setID(goodCNP+1);
        Client cl3 = new Client(goodCNP, goodDate, goodGender);
        cl3.setID(goodCNP+2);
        Client cl4 = new Client(goodCNP, goodDate, goodGender);
        cl4.setID(goodCNP+3);

        try
        {
            goodClientRepository.save(cl1);
            goodClientRepository.save(cl2);
            goodClientRepository.save(cl3);
            goodClientRepository.update(cl2);
            goodClientRepository.delete(cl3.getID());
            goodClientRepository.save(cl3);
            goodClientRepository.delete(cl3.getID());
            goodClientRepository.save(cl3);
            goodClientRepository.save(cl4);
            assertEquals("Should properly find cl1", Optional.ofNullable(cl1), goodClientRepository.findOne(cl1.getID()));
            Iterable<Client> iterabil = goodClientRepository.findAll();
            assertFalse("No entity in repository should be null", StreamSupport.stream(iterabil.spliterator(), false).anyMatch(Objects::isNull));
            System.out.println("Repository managed to work properly when dealing with valid clients");
        }
        catch(Exception exc)
        {
            System.out.println("Repository failed when working with valid clients");
        }
    }
}
