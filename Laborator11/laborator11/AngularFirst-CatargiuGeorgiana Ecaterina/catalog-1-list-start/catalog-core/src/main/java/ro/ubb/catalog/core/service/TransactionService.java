package ro.ubb.catalog.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.core.repository.TransactionRepository;

import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceInterface{

    public static final Logger log = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService()
    {

    }

    @Override
    @Transactional
    public Transaction addTransaction(Transaction t)
    {
        log.trace("Add transaction - method entered");
        return transactionRepository.save(t);
    }

    public Optional<Transaction> getTransactionByTID(Long tid)
    {

        log.trace("GetOneTransaction - method entered find book with transactionID={}", tid);
        log.trace("GetOneTransaction - method finished");
        return transactionRepository.findById(tid);
    }

    public boolean checkTransactions(Long tid)
    {

        //return transactionRepository.findOne(tid).isPresent();
        return true;
    }

    public Iterable<Transaction> getAllTransactions()
    {
        log.trace("GetAllTransactions - method entered");
        return transactionRepository.findAll();
    }

}
