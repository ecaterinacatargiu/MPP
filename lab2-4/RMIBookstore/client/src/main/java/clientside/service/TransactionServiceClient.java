package clientside.service;

import domain.Transaction;
import interfaces.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.Optional;


public class TransactionServiceClient implements TransactionServiceInterface {

    @Autowired
    private TransactionServiceInterface transactionService;


    @Override
    public void addTransaction(Transaction trans)throws RemoteException {
        transactionService.addTransaction(trans);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long tid)throws RemoteException {
        return transactionService.getTransactionById(tid);
    }

    @Override
    public Boolean checkTransaction(Long tid)throws RemoteException {
        return transactionService.checkTransaction(tid);
    }

    @Override
    public Iterable<Transaction> getAllTransactions()throws RemoteException {
        return transactionService.getAllTransactions();
    }
}
