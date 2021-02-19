package interfaces;

import domain.Transaction;
import org.springframework.stereotype.Component;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

@Component
public interface TransactionServiceInterface extends Remote {

    public void addTransaction(Transaction trans)throws RemoteException;
    public Optional<Transaction> getTransactionById(Long tid)throws RemoteException;

    public Boolean checkTransaction(Long tid)throws RemoteException;
    public Iterable<Transaction> getAllTransactions()throws RemoteException;
}
