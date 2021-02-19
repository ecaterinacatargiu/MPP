package ro.ubb.catalog.core.service;

import com.sun.tools.jconsole.JConsoleContext;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface{

    public static final Logger log = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private ClientRepository clientRepository;

    public ClientService()
    {

    }

    @Override
    @Transactional
    public Client addClient(Client client)
    {
        log.trace("Add client - method entered");
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client)
    {
        log.trace("Update client - method entered: client={}", client);
        Client c = clientRepository.findById(client.getId()).orElse(client);
        c.setCNP(client.getCNP());
        c.setGender(client.getGender());
        c.setYob(client.getYob());
        log.debug("Update Client - updated: c={}", c);
        log.trace("Update Client - method finished");
        return c;
    }

    @Override
    @Transactional
    public void deleteClient(Long clientID){
        log.trace("Delete client = method entered: clientId={}", clientID);
        if(checkClient(clientID))
        {
            clientRepository.deleteById(clientID);
        }
        else
        {
            System.out.println("Nope");
        }
        log.trace("Delete Client - method finished");
    }

    @Override
    @Transactional
    public Optional<Client> getOneClient(Long clientID)
    {

        log.trace("GetOneClient- method entered find client with clientID={}", clientID);
        log.trace("GetOneClient - method finished");
        return clientRepository.findById(clientID);
    }

    @Override
    public boolean checkClient(Long bid) {
        return true;
    }


    @Override
    @Transactional
    public Iterable<Client> getAllClients()
    {
        log.trace("GetAllClients - method entered");
        return clientRepository.findAll();
    }
}
