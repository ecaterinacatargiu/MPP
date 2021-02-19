package tests.bookstore.domain.validators;

import main.bookstore.domain.Client;
import main.bookstore.domain.validators.ClientValidator;
import main.exceptions.ValidatorException;
import org.junit.Test;

import java.lang.ref.Cleaner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestClientValidator
{
    private Client client;
    private ClientValidator validator;

    private static final Long goodCNP = 1922895321923L;
    private static final Long badCNP =  192289532192L;
    private static final String goodGender = "male";
    private static final String badGender = "pisica";
    private static final Date goodDate = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
    private static final Date badDate1 = new GregorianCalendar(2091, Calendar.MARCH, 23).getTime();
    private static final Date badDate2 = new GregorianCalendar(1842, Calendar.JULY, 9).getTime();


    public TestClientValidator()
    {
        this.validator = new ClientValidator();
    }

    @Test
    public void testValidateGoodClient() throws Exception
    {
        this.client = new Client(goodCNP, goodDate, goodGender);
        this.client.setID(goodCNP+1);
        try
        {
            this.validator.validate(this.client);
            this.client = null;
            System.out.println("Successful validation of good client");
        }
        catch(ValidatorException valEx)
        {
            this.client = null;
            throw new Exception("Unsuccessful validation of good client");
        }
    }

    @Test
    public void testValidateBadClient() throws Exception
    {
        ArrayList<Client> badClients = new ArrayList<>();

        Client cl0 = new Client(badCNP, badDate1, badGender);
        cl0.setID(badCNP);
        Client cl1 = new Client(goodCNP, goodDate, badGender);
        cl1.setID(goodCNP+1);
        Client cl2 = new Client(goodCNP, badDate1, goodGender);
        cl2.setID(goodCNP+2);
        Client cl3 = new Client(goodCNP, badDate2, goodGender);
        cl3.setID(goodCNP+3);
        Client cl4 = new Client(badCNP, goodDate, goodGender);
        cl4.setID(badCNP+1);
        Client cl5 = new Client(badCNP, badDate1, goodGender);
        cl5.setID(badCNP-1);

        badClients.add(cl0);
        badClients.add(cl1);
        badClients.add(cl2);
        badClients.add(cl3);
        badClients.add(cl4);
        badClients.add(cl5);

        for(Client cl: badClients)
        {
            try
            {
                this.validator.validate(cl);
                System.out.println("Wrongly validates a bad client");
            }
            catch(ValidatorException valEx)
            {
                System.out.println("Successful catch of bad client");
            }
        }
    }
}
