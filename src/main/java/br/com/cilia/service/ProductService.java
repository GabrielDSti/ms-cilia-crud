package br.com.cilia.service;

import br.com.cilia.controller.client.request.ClientRequest;
import br.com.cilia.controller.client.response.ClientResponse;
import br.com.cilia.controller.product.request.ProductRequest;
import br.com.cilia.controller.product.response.ProductResponse;
import br.com.cilia.converter.ClientConverter;
import br.com.cilia.converter.ProductConverter;
import br.com.cilia.exception.NotFoundException;
import br.com.cilia.repository.product.ProductRepository;
import br.com.cilia.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.cilia.utils.ConstantsErrorsUtil.EXCEPTION.MESSAGE_PRODUCT_NOT_FOUND;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse saveProduct(ProductRequest productRequest){
        validateFields(productRequest);
        var product = ProductConverter.toEntity(productRequest);
        return ProductConverter.toResponse(productRepository.save(product));
    }

    public Page<ProductResponse> findAllProduct(Pageable pageable){
        var product = productRepository.findAll(pageable);
        return ProductConverter.toPageProductResponse(product);
    }

    public List<ProductResponse> findAllProductNoPageable(){
        var product = productRepository.findAll();
        return product.stream()
                .map(ProductConverter::toResponse)
                .sorted(Comparator.comparing(ProductResponse::getId))
                .collect(Collectors.toList());

    }

    public ProductResponse findProduct(Long id){

        var product = productRepository.findById(id);
        ValidateUtils.productValidate(product);
        return ProductConverter.toResponse(product.get());
    }

    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {

        var product = productRepository.findById(id).map(prod -> {
            prod.setName(productRequest.getName());
            prod.setDescription(productRequest.getDescription());
            prod.setPrice(productRequest.getPrice());
            return productRepository.save(prod);
        });
        ValidateUtils.productValidate(product);
        return ProductConverter.toResponse(product.get());
    }

    public ProductResponse deleteProduct(Long id){

        if(!productRepository.existsById(id)) {
            throw new NotFoundException(MESSAGE_PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
        return null;
    }

    public String findProductByName(ProductRequest productRequest){
        return productRepository.findByName(productRequest.getName());
    }

    public void validateFields(ProductRequest product){
        ValidateUtils.productExistsInDbValidate(findProductByName(product));
        ValidateUtils.productNameNotNull(product);
        ValidateUtils.productPriceNotNull(product);
    }
}
