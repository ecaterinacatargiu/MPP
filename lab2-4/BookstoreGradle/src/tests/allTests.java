package tests;

import org.junit.Test;


public class allTests
{
    private ClientTest clientTest = new ClientTest();
    private BookTest bookTest = new BookTest();

    public allTests(){}

    @Test
    public void testAll()
    {
        clientTest.testGetCnp();
        clientTest.testGetID();
        clientTest.testGetYob();
        clientTest.testGetGender();

        clientTest.testSetID();
        clientTest.testSetCnp();
        clientTest.testSetYob();
        clientTest.testSetGender();

        bookTest.testGetID();
        bookTest.testGetBID();
        bookTest.testGetAuthor();
        bookTest.testGetYear();

        bookTest.testSetID();
        bookTest.testSetBID();
        bookTest.testSetAuthor();
        bookTest.testSetYear();
    }

}
