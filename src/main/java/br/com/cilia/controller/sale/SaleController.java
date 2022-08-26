package br.com.cilia.controller.sale;

import br.com.cilia.controller.sale.request.SaleRequest;
import br.com.cilia.controller.sale.response.SaleResponse;
import br.com.cilia.service.SaleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Persiste uma venda no banco.")
    public ResponseEntity<SaleResponse> save(@RequestBody SaleRequest saleRequest) {
        return new ResponseEntity<>(saleService.saveSale(saleRequest), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna todas as vendas.")
    public ResponseEntity<Page<SaleResponse>> getAllSale(Pageable pageable) {
        return new ResponseEntity<>(saleService.findAllSale(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna uma venda por Id.")
    public ResponseEntity<SaleResponse> getSale(@PathVariable Long id) {
        return new ResponseEntity<>(saleService.findSale(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza uma venda por Id.")
    public ResponseEntity<SaleResponse> updateSale(@RequestBody SaleRequest saleRequest, @PathVariable Long id) {
        return new ResponseEntity<>(saleService.updateSale(saleRequest, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta uma venda por Id.")
    public ResponseEntity<SaleResponse> deleteClient(@PathVariable Long id) {
        return new ResponseEntity<>(saleService.deleteSale(id), HttpStatus.OK);
    }


}
