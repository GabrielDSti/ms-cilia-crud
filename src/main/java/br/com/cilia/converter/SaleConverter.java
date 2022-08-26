package br.com.cilia.converter;

import br.com.cilia.controller.sale.request.SaleProductRequest;
import br.com.cilia.entity.product.Product;
import br.com.cilia.entity.sale.Sale;
import br.com.cilia.controller.sale.request.SaleRequest;
import br.com.cilia.controller.sale.response.SaleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class SaleConverter {


    private SaleConverter(){

    }

    /*
     * Converter SaleRequest p/ Sale
     */
    public static Sale toEntity(SaleRequest saleRequest){
        return Sale.builder()
                .client(ClientConverter.toSaleClientEntity(saleRequest.getClientRequest()))
                .products(saleRequest.getSaleProductRequests()
                        .stream()
                        .map(ProductConverter::toSaleProductEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    /*
     * Converter Sale p/ SaleResponse
     */
    public static SaleResponse toResponse(Sale sale){
        return SaleResponse.builder()
                .id(sale.getId())
                .clientResponse(ClientConverter.toResponse(sale.getClient()))
                .productResponses(sale.getProducts()
                        .stream()
                        .map(ProductConverter::toResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<Product> toListProduct(List<SaleProductRequest> saleProductRequests){
        return saleProductRequests.stream().map(ProductConverter::toSaleProductEntity).collect(Collectors.toList());
    }

    public static Page<SaleResponse> toPageSaleResponse(Page<Sale> sale){
        var list = sale.stream().map(SaleConverter::toResponse).collect(Collectors.toList());
        return new PageImpl<>(list);
    }
}
