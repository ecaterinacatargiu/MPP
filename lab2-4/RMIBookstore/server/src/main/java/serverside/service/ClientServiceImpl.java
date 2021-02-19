package serverside.service;

import domain.Client;
import domain.validators.ClientValidator;
import domain.validators.Validator;
import interfaces.ClientServiceInterface;
import repository.InMemoryRepository;

import java.rmi.RemoteException;
import java.util.Optional;

public class ClientServiceImpl implements ClientServiceInterface {

    private InMemoryRepository<Long, Client> clientRepository;

    public ClientServiceImpl()
    {
        Validator<Client> v = new ClientValidator();
        this.clientRepository = new InMemoryRepository<Long, Client>(v);

    }

    @Override
    public void addClient(Client client) throws RemoteException {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client)throws RemoteException {
        clientRepository.update(client);
    }

    @Override
    public void deleteClient(Long clientID)throws RemoteException {
        clientRepository.delete(clientID);
    }

    @Override
    public Optional<Client> getOneClient(Long clientID)throws RemoteException {
        return clientRepository.findOne(clientID);
    }

    @Override
    public Boolean checkClient(Long cnp)throws RemoteException {
        return clientRepository.findOne(cnp).isPresent();
    }

    @Override
    public Iterable<Client> getAllClients()throws RemoteException {
        return clientRepository.findAll();
    }
}
