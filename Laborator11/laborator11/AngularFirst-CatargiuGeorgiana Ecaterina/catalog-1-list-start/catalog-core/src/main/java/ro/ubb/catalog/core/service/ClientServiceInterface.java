package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Client;

import java.util.Optional;

public interface ClientServiceInterface {

    public Client addClient(Client client);
    public Client updateClient(Client client);
    public void deleteClient(Long clientID);
    public Optional<Client> getOneClient(Long clientID);
    public boolean checkClient(Long bid);
    public Iterable<Client> getAllClients();
}
