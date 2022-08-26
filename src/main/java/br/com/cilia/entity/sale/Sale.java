package br.com.cilia.entity.sale;

import br.com.cilia.entity.client.Client;
import br.com.cilia.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SALE")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Client is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


   // @NotBlank(message = "Products is mandatory")
    @Type(type = "jsonb")
    @Column(name= "products", columnDefinition = "jsonb")
    private List<Product> products;

}
