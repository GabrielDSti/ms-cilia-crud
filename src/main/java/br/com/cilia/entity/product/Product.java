package br.com.cilia.entity.product;

import br.com.cilia.entity.sale.Sale;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message = "Name is mandatory")
    private String name;


    private String description;

    //@NotBlank(message = "Price is mandatory")
    private Double price;

}
