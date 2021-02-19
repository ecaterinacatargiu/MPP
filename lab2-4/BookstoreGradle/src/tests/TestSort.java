package tests;

import main.bookstore.domain.Book;
import main.bookstore.domain.Client;
import main.repository.Sort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestSort
{
    @Test
    public void testReflection() throws Exception
    {
        Client newCli = new Client(1922895321924L, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "male");
        Sort sorteaza = new Sort("CNP", "gender");
    }

    @Test
    public void testClients() throws Exception
    {
        ArrayList<Client> haida = new ArrayList<>();
        Sort sorteaza = new Sort(Sort.Direction.DESC, "yob", "id");

        Client newCl1 = new Client(1922895321924L, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "male");
        newCl1.setID(1922895321924L);
        Client newCl2 = new Client(1922895321925L, new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime(), "male");
        newCl2.setID(1922895321925L);
        Client newCl3 = new Client(1922895321926L, new GregorianCalendar(2015, Calendar.FEBRUARY, 11).getTime(), "male");
        newCl3.setID(1922895321926L);
        Client newCl4 = new Client(1922895321927L, new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), "male");
        newCl4.setID(1922895321927L);
        haida.add(newCl4);
        haida.add(newCl2);
        haida.add(newCl3);
        haida.add(newCl1);
        /*haida.stream().forEach(System.out::println);
        System.out.println("---------------------------------------------\n");
        haida.sort(sorteaza);
        haida.stream().forEach(System.out::println);*/
        assertEquals(haida.get(0), newCl4);
        assertEquals(haida.get(1), newCl2);
        assertEquals(haida.get(2), newCl3);
        assertEquals(haida.get(3), newCl1);

        haida.sort(sorteaza);

        assertEquals(haida.get(0), newCl3);
        assertEquals(haida.get(1), newCl2);
        assertEquals(haida.get(2), newCl4);
        assertEquals(haida.get(3), newCl1);
    }
}
