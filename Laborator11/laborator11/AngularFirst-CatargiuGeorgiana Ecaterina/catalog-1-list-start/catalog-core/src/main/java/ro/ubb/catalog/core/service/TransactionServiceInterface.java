package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Transaction;

import java.util.Optional;


public interface TransactionServiceInterface {

    public Transaction addTransaction(Transaction t);
    public Optional<Transaction> getTransactionByTID(Long tid);
    public boolean checkTransactions(Long tid);
    public Iterable<Transaction> getAllTransactions();

}
