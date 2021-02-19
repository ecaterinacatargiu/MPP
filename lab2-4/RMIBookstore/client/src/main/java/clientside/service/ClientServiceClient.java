package clientside.service;

import domain.Client;
import interfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.RemoteException;
import java.util.Optional;


public class ClientServiceClient implements ClientServiceInterface {

    @Autowired
    private ClientServiceInterface clientService;

    @Override
    public void addClient(Client client)throws RemoteException {
        clientService.addClient(client);
    }

    @Override
    public void updateClient(Client client)throws RemoteException {
        clientService.updateClient(client);
    }

    @Override
    public void deleteClient(Long clientID)throws RemoteException {
        clientService.deleteClient(clientID);
    }

    @Override
    public Optional<Client> getOneClient(Long clientID)throws RemoteException {
        return clientService.getOneClient(clientID);
    }

    @Override
    public Boolean checkClient(Long cnp)throws RemoteException {
        return clientService.checkClient(cnp);
    }

    @Override
    public Iterable<Client> getAllClients()throws RemoteException {
        return clientService.getAllClients();
    }
}
