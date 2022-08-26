package br.com.cilia.converter;

import br.com.cilia.controller.client.request.ClientRequest;
import br.com.cilia.controller.client.response.ClientResponse;
import br.com.cilia.controller.sale.request.SaleClientRequest;
import br.com.cilia.entity.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

public class ClientConverter {

    private ClientConverter(){

    }

    /*
     * Converter ClientRequest p/ Client
     */
    public static Client toEntity(ClientRequest clientRequest){
        return Client.builder()
                .name(clientRequest.getName())
                .email(clientRequest.getEmail())
                .birthDate(clientRequest.getBirthDate())
                .build();
    }

    public static Client toSaleClientEntity(SaleClientRequest saleClientRequest){
        return Client.builder()
                .id(saleClientRequest.getId())
                .name(saleClientRequest.getName())
                .email(saleClientRequest.getEmail())
                .build();
    }

    /*
     * Converter Client p/ ClientResponse
     */
    public static ClientResponse toResponse(Client client){
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .birthDate(client.getBirthDate())
                .build();
    }

    public static Page<ClientResponse> toPageClientResponse(Page<Client> client){
        var list = client.stream().map(ClientConverter::toResponse).collect(Collectors.toList());
        return new PageImpl<>(list);
    }
}
