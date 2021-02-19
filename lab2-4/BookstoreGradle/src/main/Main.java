package main;

import main.bookstore.domain.Book;
import main.service.BookPublisher;
import main.bookstore.domain.Client;
import main.bookstore.domain.Transaction;
import main.bookstore.domain.validators.BookValidator;
import main.bookstore.domain.validators.ClientValidator;
import main.bookstore.domain.validators.TransactionValidator;
import main.bookstore.domain.validators.Validator;

import main.repository.InMemoryRepository;

import main.service.BookService;
import main.service.ClientService;
import main.service.TransactionService;
import main.ui.Console;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        BookService bs= new BookService();
        ClientService cs = new ClientService();
        TransactionService ts = new TransactionService();

        Date data = new GregorianCalendar(2010, Calendar.MARCH, 15).getTime();

        BookPublisher bookPublisher = new BookPublisher(1L,data,cs,bs,ts,1L);


        Console consola = new Console(bookPublisher);
        consola.talkToUser();
        //try with resources to close/save fileRepo
    }
}
