package clientside;

import domain.Book;
import domain.Client;
import domain.Transaction;
import interfaces.BookPublisherInterface;

import java.rmi.RemoteException;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class UIConsole {

    private Scanner scanner;
    private BookPublisherInterface bookPublisherInterface;

    public UIConsole(BookPublisherInterface bookPublisherInterface)
    {

        this.bookPublisherInterface = bookPublisherInterface;
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
            valid = IntStream.of(0,1,2,3,4,5,6,7,8,9,10,11,12).anyMatch(x -> x == finalValoarea);
        }
        return valoarea;
    }

    private void showMenu()
    {
        System.out.print("Welcome to the CharisMIC bookstore. We are non-GDPR compliant.\nWhat do you wish to do next?\n");
        System.out.print("1.Add a client.\n2.Add a book.\n3.Show all clients.\n4.Show all books.\n5.Delete a client.\n6.Delete a book.\n");
        System.out.print("7.Update a client.\n8.Update a book.\n9.Check if we have a book by it's ISBN.\n10.Check if we have a client based on CNP.\n");
        System.out.print("11.Make a transaction\n12.Show all transactions\n");
        System.out.print("0.Run away.\n>>> ");
    }

    private void addClient() throws RemoteException {

        System.out.println("\nEnter the CNP: ");
        long cnp = this.scanner.nextLong();
        this.scanner.nextLine();

        System.out.println("\nEnter date of birth: ");
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
        System.out.println("\nEnter gender: ");
        String gender = this.scanner.nextLine();
        this.scanner.nextLine();

        this.bookPublisherInterface.addClient(cnp,dateOfBirth,gender);
    }

    private void updateClient() throws RemoteException {

        /*System.out.println("\nEnter cnp: ");
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

        this.bookPublisherInterface.updateClient(cnp, dateOfBirth, gender);

        System.out.println("Client successfully updated");*/

        System.out.println("\nEnter client cnp: ");
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

        this.bookPublisherInterface.updateClient(cnp, dateOfBirth, gender);

        System.out.println("Client successfully updated");
    }


    public void deleteClient() throws RemoteException
    {
        System.out.println("Enter client cnp:");
        Long cnp = scanner.nextLong();

        this.bookPublisherInterface.deleteClient(cnp);

        System.out.println("Client successfully deleted");
    }

    public void checkClient() throws RemoteException {
        System.out.println("\nEnter client cnp:");
        Long cnp = scanner.nextLong();

       this.bookPublisherInterface.checkClient(cnp);

       if(this.bookPublisherInterface.checkClient(cnp))
       {
           System.out.println("Client is here");
       }
       else
       {
           System.out.println("Client is not here");
       }
    }


    public void showAllClients() throws RemoteException
    {
        Set<Client> st = this.bookPublisherInterface.getAllClients();
        this.bookPublisherInterface.getAllClients()
                .forEach(System.out::println);
    }

    public void addBook() throws RemoteException {
        System.out.println("\nEnter book id: ");
        long bid = this.scanner.nextLong();
        this.scanner.nextLine();

        System.out.println("\nEnter author: ");
        String author = this.scanner.nextLine();

        System.out.println("\nEnter year: ");
        int year = this.scanner.nextInt();
        this.scanner.nextLine();

        this.bookPublisherInterface.addBook(bid,author,year);

        System.out.println("Book successfully added");
    }

    public void updateBook() throws RemoteException {
        System.out.println("\nEnter book id: ");
        Long bid = scanner.nextLong();
        scanner.nextLine();

        System.out.println("\nEnter new author: ");
        String author = scanner.nextLine();

        System.out.println("\nEnter new year:");
        int year = scanner.nextInt();

        this.bookPublisherInterface.updateBook(bid, author, year);

        System.out.println("Book successfully updated");
    }

    public void deleteBook() throws RemoteException {
        System.out.println("Enter book id:");
        Long bID = scanner.nextLong();
        this.bookPublisherInterface.deleteBook(bID);

        System.out.println("Book successfully deleted");

    }

    public void checkBook() throws RemoteException {
        System.out.println("\nEnter book id:");
        Long bid = scanner.nextLong();

        this.bookPublisherInterface.checkBook(bid);

        if(this.bookPublisherInterface.checkBook(bid))
        {
            System.out.println("Book is here");
        }
        else
        {
            System.out.println("Book is not here");
        }
    }

    public void showAllBooks() throws RemoteException {
        Set<Book> st = this.bookPublisherInterface.getAllBooks();
        this.bookPublisherInterface.getAllBooks()
                .forEach(System.out::println);
    }

    public void makeTransaction() throws RemoteException {
        System.out.println("\n Enter client cnp: ");
        Long cnp = scanner.nextLong();

        System.out.println("\nEnter book id:");
        Long bid = scanner.nextLong();

        System.out.print("\nEnter price of acquisition: ");
        Integer price = scanner.nextInt();
        scanner.nextLine();

        this.bookPublisherInterface.makeTransaction(cnp,bid,price);

        System.out.println("Transaction successfully added");
    }

    void showTransactions() throws RemoteException {
        Set<Transaction> tr = this.bookPublisherInterface.getAllTransactions();
      this.bookPublisherInterface.getAllTransactions()
              .forEach(System.out::println);
    }

    public void talkToUser() throws RemoteException {
        int optiune = 0;
        this.showMenu();
        while(0 != (optiune = getUserInput()))
        {
            switch(optiune)
            {
                case 1:
                    this.addClient();
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
                default:
                    System.out.print("\nNot implemented yet :D\n");

            }
            System.out.print("\n");
            this.showMenu();
        }
    }










}
