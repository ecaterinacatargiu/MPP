package main;

import main.bookstore.domain.Book;
import main.bookstore.domain.Client;
import main.bookstore.domain.Transaction;

import main.service.BookPublisher;
import main.service.BookService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class UIConsole
{
    private Scanner scanner;
    private PublisherServiceInterface thisService;
    private ClientConsole cconsole;

    public UIConsole(PublisherServiceInterface thisService, ClientConsole cconsole)
    {
        this.thisService = thisService;
        this.cconsole = cconsole;
        this.scanner = new Scanner(System.in);
    }

    private int getUserInput()
    {
        boolean valid = false;
        int valoarea = 0;
        while(!valid)
        {
            valoarea = this.scanner.nextInt();
            int finalValoarea = valoarea;
            valid = IntStream.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14).anyMatch(x -> x == finalValoarea);
        }
        return valoarea;
    }

    private void showMenu()
    {
        System.out.print("Welcome to the CharisMIC bookstore. We are non-GDPR compliant.\nWhat do you wish to do next?\n");
        System.out.print("1.Add a client.\n2.Add a book.\n3.Show all clients.\n4.Show all books.\n5.Delete a client.\n6.Delete a book.\n");
        System.out.print("7.Update a client.\n8.Update a book.\n9.Check if we have a book by it's ISBN.\n10.Check if we have a client based on CNP.\n");
        System.out.print("11.Make a transaction\n12.Show all transactions\n13.Show clients based on spending.\n14.Show all transactions before a date.\n");
        System.out.print("0.Run away.\n>>> ");
    }

    private void getInputClient()
    {
        System.out.print("\nEnter the CNP >> ");
        long CNP = this.scanner.nextLong();
        this.scanner.nextLine();

        System.out.print("\nEnter the year of birth (in format dd-mm-yyyy please) >> ");
        Date dateOfBirth = null;
        String date= this.scanner.nextLine();
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        try
        {
            dateOfBirth= format.parse(date);
        }
        catch(ParseException parEx)
        {
            System.out.print("Bad date of birth entered.");
            return;
        }

        System.out.print("\nEnter the gender >> ");
        String gender = this.scanner.nextLine();

        String body = Long.toString(CNP) + ", " + dateOfBirth.toString() + ", " + gender;

        Future<String> future = this.thisService.addClient(body);
        this.cconsole.daemonExecute(future);
    }

    public void showAllClients()
    {
        Future<String> future = this.thisService.showClients(null);
        this.cconsole.daemonExecute(future);
    }

    public void addBook()
    {
        System.out.println("\nEnter book id: ");
        long bid = this.scanner.nextLong();
        this.scanner.nextLine();
        System.out.println("\nEnter author: ");
        String author = this.scanner.nextLine();
        System.out.println("\nEnter year: ");
        int year = this.scanner.nextInt();
        this.scanner.nextLine();

        String body = bid + ", " + author + ", " + Integer.toString(year);
        Future<String> future = this.thisService.addBook(body);
        this.cconsole.daemonExecute(future);

    }

    public void showAllBooks()
    {
        Future<String> future = this.thisService.showBooks(null);
        this.cconsole.daemonExecute(future);
    }

    public void deleteClient()
    {
        System.out.println("Enter client cnp:");
        Long cnp = scanner.nextLong();

        String body = Long.toString(cnp);

        Future<String> future = this.thisService.deleteClient(body);
        this.cconsole.daemonExecute(future);
    }


    public void deleteBook()
    {
        System.out.println("Enter book id:");
        Long bID = scanner.nextLong();

        String body = Long.toString(bID);

        Future<String> future = this.thisService.deleteBook(body);
        this.cconsole.daemonExecute(future);
    }


    public void updateClient()
    {
        System.out.println("\nEnter cnp: ");
        Long cnp = scanner.nextLong();
        scanner.nextLine();

        System.out.println("\nEnter new date of birth: ");
        Date dateOfBirth = null;
        String date= this.scanner.nextLine();
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        try
        {
            dateOfBirth= format.parse(date);
        }
        catch(ParseException parEx)
        {
            System.out.print("Bad date of birth entered.");
            return;
        }

        System.out.println("\nEnter new gender:");
        String gender = scanner.nextLine();

        String body = Long.toString(cnp) + ", " + dateOfBirth.toString() + ", " + gender;

        Future<String> future = this.thisService.updateClient(body);
        this.cconsole.daemonExecute(future);
    }

    public void updateBook()
    {
        System.out.println("\nEnter book id: ");
        Long bid = scanner.nextLong();
        scanner.nextLine();

        System.out.println("\nEnter new author: ");
        String author = scanner.nextLine();

        System.out.println("\nEnter new year:");
        int year = scanner.nextInt();


        String body = Long.toString(bid) + ", " + author + ", " + year;

        Future<String> future = this.thisService.updateBook(body);
        this.cconsole.daemonExecute(future);
    }

    public void checkBook()
    {
        System.out.println("\nEnter book id:");
        Long bid = scanner.nextLong();

        String body = Long.toString(bid);

        Future<String> future = this.thisService.checkBook(body);
        this.cconsole.daemonExecute(future);
    }

    public void checkClient()
    {
        System.out.println("\nEnter client cnp:");
        Long cnp = scanner.nextLong();

        String body = Long.toString(cnp);

        Future<String> future = this.thisService.checkClient(body);
        this.cconsole.daemonExecute(future);
    }

    public void makeTransaction()
    {
        System.out.println("\n Enter client cnp: ");
        Long cnp = scanner.nextLong();


        System.out.println("\nEnter book id:");
        Long bid = scanner.nextLong();


        System.out.print("\nEnter price of acquisition: ");
        Integer price = scanner.nextInt();
        scanner.nextLine();

        String body = Long.toString(cnp) + ", " + Long.toString(bid) + ", " + Integer.toString(price);

        Future<String> future = this.thisService.makeTransaction(body);
        this.cconsole.daemonExecute(future);

    }
    void showTransactions()
    {
        String body = null;

        Future<String> future = this.thisService.showTransactions(body);
        this.cconsole.daemonExecute(future);

    }

    void showTransactionsBefore()
    {
        System.out.print("\nEnter the date (dd-mm-yyyy) >> ");
        this.scanner.nextLine();
        Date dateBefore = null;
        String date= this.scanner.nextLine();
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        try
        {
            dateBefore= format.parse(date);
        }
        catch(ParseException parEx)
        {
            System.out.print("Bad date of birth entered.");
            return;
        }

        String body = dateBefore.toString();

        Future<String> future = this.thisService.transactBefore(body);
        this.cconsole.daemonExecute(future);
    }

    void showClientBySpending()
    {
        String body = null;

        Future<String> future = this.thisService.clientSpending(body);
        this.cconsole.daemonExecute(future);
    }

    public void talkToUser()
    {
        int optiune = 0;
        this.showMenu();
        while(0 != (optiune = getUserInput()))
        {
            switch(optiune)
            {
                case 1:
                    this.getInputClient();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    this.showAllClients();
                    break;
                case 4:
                    showAllBooks();
                    break;
                case 5:
                    deleteClient();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    updateClient();
                    break;
                case 8:
                    updateBook();
                    break;
                case 9:
                    checkBook();
                    break;
                case 10:
                    checkClient();
                    break;
                case 11:
                    makeTransaction();
                    break;
                case 12:
                    showTransactions();
                    break;
                case 13:
                    this.showClientBySpending();
                    break;
                case 14:
                    this.showTransactionsBefore();
                    break;

                default:
                    System.out.print("\nNot implemented yet :D\n");
                /*case 2:
                    this.getInputBook();
                case 3:
                    this.showAllClients();
                case 4:
                    this.showAllBooks();*/

            }
            System.out.print("\n");
            this.showMenu();
        }
    }
}
