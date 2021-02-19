package ro.ubb.catalog.web.controller;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.service.ClientServiceInterface;
import ro.ubb.catalog.web.converter.ClientConverter;
import ro.ubb.catalog.web.dto.ClientDto;
import ro.ubb.catalog.web.dto.ClientsDto;

import java.util.Collection;

@RestController
public class ClientController {

    public static final Logger log= LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value="/clients", method = RequestMethod.GET)
    ClientsDto getAllClients()
    {
        return new ClientsDto(clientConverter
                .convertModelsToDtos((Collection<Client>) clientService.getAllClients()));
    }

    @SneakyThrows
    @RequestMapping(value="/clients", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto clientDto)
    {
        return clientConverter.convertModelToDto(clientService.addClient(
                clientConverter.convertDtoToModel(clientDto)
        ));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        Client c = clientConverter.convertDtoToModel(clientDto);
        c.setId(id);

        return clientConverter.convertModelToDto(clientService.updateClient(c));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
