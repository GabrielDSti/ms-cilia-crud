package br.com.cilia.controller.client.response;

import br.com.cilia.entity.client.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;

    public ClientResponse(Client client) {
        // TODO document why this constructor is empty
    }
}
