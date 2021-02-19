package tests;

import main.bookstore.domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientTest
{
    private static final Long id = 1L;
    private static final Long otherId = 2L;

    private static final Long cnp = 1234567890123L;
    private static final Long othercnp = 9876543210987L;

    private static final String gender = "male";
    private static final String otherGender= "female";

    private static final Date yob = new Date(1999,10,18);
    private static final Date otherYob = new Date(2009,12,6);

    private Client client= new Client(cnp,yob,gender);

    public ClientTest(){client.setID(id);}

    @Before
    public void setUp() throws Exception
    {

        client = new Client(cnp,yob,gender);
        client.setID(id);
    }

    @After
    public void tearDown() throws Exception
    {
        client = null;
    }

    //test getters
    @Test
    public void testGetID()
    {
        assertEquals("client get id",client.getID(),id);
    }

    @Test
    public void testGetCnp()
    {
        assertEquals("get cnp",(Object)cnp,client.getCNP());
    }

    @Test
    public void testGetYob()
    {
        assertEquals("get yob",yob,client.getYob());
    }

    @Test
    public void testGetGender()
    {
        assertEquals("get gender",gender,client.getGender());
    }

    //test setters

    @Test
    public void testSetID()
    {
        client.setID(otherId);
        assertEquals("client set id",client.getID(),otherId);
    }

    @Test
    public void testSetCnp()
    {
        client.setCNP(othercnp);
        assertEquals("client set cnp ",(Object) client.getCNP(),othercnp);
    }

    @Test
    public void testSetYob()
    {
        client.setYob(otherYob);
        assertEquals("client set year",otherYob,client.getYob());
    }

    @Test
    public void testSetGender()
    {
        client.setGender(otherGender);
        assertEquals("client set gender",otherGender,client.getGender());
    }

}
