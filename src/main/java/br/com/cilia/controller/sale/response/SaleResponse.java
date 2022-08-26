package br.com.cilia.controller.sale.response;

import br.com.cilia.controller.client.response.ClientResponse;
import br.com.cilia.controller.product.response.ProductResponse;
import br.com.cilia.entity.client.Client;
import br.com.cilia.entity.product.Product;
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
public class SaleResponse {

    private Long id;

    @JsonProperty("client")
    private ClientResponse clientResponse;

    @JsonProperty("products")
    private List<ProductResponse> productResponses;

}
