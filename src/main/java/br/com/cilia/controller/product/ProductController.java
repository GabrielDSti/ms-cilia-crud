package br.com.cilia.controller.product;

import br.com.cilia.controller.product.request.ProductRequest;
import br.com.cilia.controller.product.response.ProductResponse;
import br.com.cilia.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Persiste um produto no banco.")
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna todos os produtos.")
    public ResponseEntity<Page<ProductResponse>> getAllClient(Pageable pageable) {
        return new ResponseEntity<>(productService.findAllProduct(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna todos os produtos sem paginação.")
    public ResponseEntity<List<ProductResponse>> getAllClient() {
        return new ResponseEntity<>(productService.findAllProductNoPageable(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna um produto por Id.")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProduct(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Atualiza um produto por Id.")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        return new ResponseEntity<>(productService.updateProduct(productRequest, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deleta um produto por Id.")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
