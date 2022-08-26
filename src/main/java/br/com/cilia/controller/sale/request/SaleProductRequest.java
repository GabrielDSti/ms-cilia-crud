package br.com.cilia.controller.sale.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleProductRequest {

    private Long id;
    private String name;
    private String description;
    private Double price;
}