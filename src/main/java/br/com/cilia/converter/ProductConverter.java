package br.com.cilia.converter;

import br.com.cilia.controller.product.request.ProductRequest;
import br.com.cilia.controller.product.response.ProductResponse;
import br.com.cilia.controller.sale.request.SaleProductRequest;
import br.com.cilia.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

public class ProductConverter {

    private ProductConverter(){

    }

    /*
     * Converter ProductRequest p/ Product
     */
    public static Product toEntity(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public static Product toSaleProductEntity(SaleProductRequest saleProductRequest){
        return Product.builder()
                .id(saleProductRequest.getId())
                .name(saleProductRequest.getName())
                .description(saleProductRequest.getDescription())
                .price(saleProductRequest.getPrice())
                .build();
    }

    /*
     * Converter Product p/ ProductResponse
     */
    public static ProductResponse toResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Page<ProductResponse> toPageProductResponse(Page<Product> product){
        var list = product.stream().map(ProductConverter::toResponse).collect(Collectors.toList());
        return new PageImpl<>(list);
    }


}
