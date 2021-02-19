package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .CNP(dto.getCNP())
                .yob(dto.getYob())
                .gender(dto.getGender())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .CNP(client.getCNP())
                .yob(client.getYob())
                .gender(client.getGender())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
