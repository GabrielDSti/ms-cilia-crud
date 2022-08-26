package br.com.cilia.controller.client;

import br.com.cilia.controller.client.request.ClientRequest;
import br.com.cilia.controller.client.response.ClientResponse;
import br.com.cilia.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/client")
@Api(value = "API REST Cilia Produtos")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Persiste um cliente no banco.")
    public ResponseEntity<ClientResponse> save(@RequestBody ClientRequest clientRequest) {
        return new ResponseEntity<>(clientService.saveClient(clientRequest), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna todos os clientes.")
    public ResponseEntity<Page<ClientResponse>> getAllClient(Pageable pageable) {
        return new ResponseEntity<>(clientService.findAllClient(pageable), HttpStatus.OK);
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna um cliente por Id.")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.findClient(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um cliente por Id.")
    public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientRequest clientRequest, @PathVariable Long id) {
        return new ResponseEntity<>(clientService.updateClient(clientRequest, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta um cliente por Id.")
    public ResponseEntity<ClientResponse> deleteClient(@PathVariable Long id) {
        return new ResponseEntity<>(clientService.deleteClient(id), HttpStatus.OK);
    }

}
