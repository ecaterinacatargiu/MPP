package interfaces;

import domain.Client;
import org.springframework.stereotype.Component;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

@Component
public interface ClientServiceInterface extends Remote {

    public void addClient(Client client)throws RemoteException;
    public void updateClient(Client client)throws RemoteException;
    public void deleteClient(Long clientID)throws RemoteException;

    public Optional<Client> getOneClient(Long clientID)throws RemoteException;
    public Boolean checkClient(Long cnp)throws RemoteException;
    public Iterable<Client> getAllClients()throws RemoteException;



}
