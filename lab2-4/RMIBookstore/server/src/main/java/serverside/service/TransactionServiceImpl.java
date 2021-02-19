package serverside.service;

import domain.Transaction;
import domain.validators.TransactionValidator;
import domain.validators.Validator;
import interfaces.TransactionServiceInterface;
import repository.InMemoryRepository;

import java.rmi.RemoteException;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionServiceInterface {

    private InMemoryRepository<Long, Transaction> transactionRepository;

    public TransactionServiceImpl()
    {
        Validator<Transaction> v = new TransactionValidator();
        this.transactionRepository = new InMemoryRepository<Long, Transaction>(v);
    }

    @Override
    public void addTransaction(Transaction trans)throws RemoteException {
        transactionRepository.save(trans);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long tid)throws RemoteException {
        return transactionRepository.findOne(tid);
    }

    @Override
    public Boolean checkTransaction(Long tid)throws RemoteException {
        return transactionRepository.findOne(tid).isPresent();
    }

    @Override
    public Iterable<Transaction> getAllTransactions()throws RemoteException {
        return transactionRepository.findAll();
    }
}
