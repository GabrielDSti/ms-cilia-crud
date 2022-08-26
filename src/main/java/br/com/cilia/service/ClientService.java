package br.com.cilia.service;

import br.com.cilia.controller.client.request.ClientRequest;
import br.com.cilia.controller.client.response.ClientResponse;
import br.com.cilia.converter.ClientConverter;
import br.com.cilia.exception.NotFoundException;
import br.com.cilia.repository.client.ClientRepository;
import br.com.cilia.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.cilia.utils.ConstantsErrorsUtil.EXCEPTION.MESSAGE_CLIENT_NOT_FOUND;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientResponse saveClient(ClientRequest clientRequest){
        validateFields(clientRequest);
        var client = ClientConverter.toEntity(clientRequest);
        return ClientConverter.toResponse(clientRepository.save(client));
    }

    public Page<ClientResponse> findAllClient(Pageable pageable){
        var client = clientRepository.findAll(pageable);
        return ClientConverter.toPageClientResponse(client);
    }

    public ClientResponse findClient(Long id){

        var client = clientRepository.findById(id);
        ValidateUtils.clientValidate(client);
        return ClientConverter.toResponse(client.get());
    }

    public ClientResponse updateClient(ClientRequest clientRequest,Long id) {

        var client = clientRepository.findById(id).map(cli -> {
            cli.setName(clientRequest.getName());
            cli.setEmail(clientRequest.getEmail());
            cli.setBirthDate(clientRequest.getBirthDate());
            return clientRepository.save(cli);
        });
        ValidateUtils.clientValidate(client);
        return ClientConverter.toResponse(client.get());
    }

    public ClientResponse deleteClient(Long id){

        if(!clientRepository.existsById(id)) {
            throw new NotFoundException(MESSAGE_CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
        return null;
    }

    public String findClientByEmail(ClientRequest clientRequest){
        return clientRepository.findByEmail(clientRequest.getEmail());
    }

    public void validateFields(ClientRequest client){
        ValidateUtils.clientExistsInDbValidate(findClientByEmail(client));
        ValidateUtils.clientNameNotNull(client);
        ValidateUtils.clientEmailNotNull(client);
    }
}
