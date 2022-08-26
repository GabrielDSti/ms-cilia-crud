package br.com.cilia.controller.sale.request;

import br.com.cilia.controller.client.request.ClientRequest;
import br.com.cilia.controller.product.request.ProductRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleRequest {

    @JsonProperty("client")
    private SaleClientRequest clientRequest;

    @JsonProperty("products")
    private List<SaleProductRequest> saleProductRequests;
}
