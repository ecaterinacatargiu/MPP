package main.service;

import main.bookstore.domain.Client;
import main.bookstore.domain.validators.ClientValidator;
import main.repository.InMemoryRepository;
import main.repository.Repository;

import java.sql.SQLException;
import java.util.Optional;

public class ClientService {

    private Repository<Long,Client> clientRepository;

    public ClientService()
    {
        ClientValidator v = new ClientValidator();
        this.clientRepository = new InMemoryRepository<>(v);
    }


    public void addClient(Client client) throws Exception
    {
        clientRepository.save(client);
    }

    public void updateClient(Client client) throws Exception
    {
        clientRepository.update(client);
    }

    public void deleteClient(Long clientID) throws SQLException {
        clientRepository.delete(clientID);
    }

    public Optional<Client> getOneClient(Long clientID)
    {
        return clientRepository.findOne(clientID);
    }

    public boolean checkClient(Long cnp)
    {
        return clientRepository.findOne(cnp).isPresent();
    }

    public Iterable<Client> getAllClients()
    {
        return clientRepository.findAll();
    }
}
