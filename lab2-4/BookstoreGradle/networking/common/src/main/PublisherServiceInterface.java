package main;

import java.util.concurrent.Future;

public interface PublisherServiceInterface
{
    String ADD_BOOK = "addBook"; // body: BID, author, year
    String DELETE_BOOK = "deleteBook"; // body: BID
    String ADD_CLIENT = "addClient"; // body: CNP long, yob Date, gender String
    String DELETE_CLIENT = "deleteClient"; // body: CNP
    String SHOW_CLIENTS = "showClients"; // body: null
    String SHOW_BOOKS = "showBooks"; // body: null
    String UP_CLIENT = "updateClient"; // body: CNP, yob, gender
    String UP_BOOK = "updateBook"; // body: BID, author, year
    String CHECK_BOOK = "checkBook"; // body: BID
    String CHECK_CLIENT = "checkClient"; // body: CNP
    String DO_TRANSACT = "makeTransaction"; // body: CNP, BID, price
    String SHOW_TRANSACT = "showTransactions"; // body: null
    String CLIENTS_SPENDING = "clientSpending"; // body: null
    String TRANSACT_BEFORE_DATE = "transactBefore"; // body: date ["dd-mm-yyyy"]

    Future<String> addBook(String body);
    Future<String> deleteBook(String body);
    Future<String> addClient(String body);
    Future<String> deleteClient(String body);
    Future<String> showClients(String body);
    Future<String> showBooks(String body);
    Future<String> updateClient(String body);
    Future<String> updateBook(String body);
    Future<String> checkBook(String body);
    Future<String> checkClient(String body);
    Future<String> makeTransaction(String body);
    Future<String> showTransactions(String body);
    Future<String> clientSpending(String body);
    Future<String> transactBefore(String body);
}
