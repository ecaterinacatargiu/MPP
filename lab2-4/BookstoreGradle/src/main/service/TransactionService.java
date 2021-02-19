package main.service;

import main.bookstore.domain.Transaction;
import main.bookstore.domain.validators.TransactionValidator;
import main.repository.InMemoryRepository;
import main.repository.Repository;

import java.util.Optional;

public class TransactionService {

    private Repository<Long, Transaction> transactionRepository;

    public TransactionService()
    {
        TransactionValidator v = new TransactionValidator();
        this.transactionRepository = new InMemoryRepository<>(v);
    }

    public void addTransaction(Transaction t)
    {
        transactionRepository.save(t);
    }

    public Optional<Transaction> getTransactionByTID(Long tid)
    {
        return transactionRepository.findOne(tid);
    }

    public boolean checkTransactions(Long tid)
    {
        return transactionRepository.findOne(tid).isPresent();
    }

    public Iterable<Transaction> getAllTransactions()
    {
        return transactionRepository.findAll();
    }

}
