package br.com.cilia.controller.client.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRequest {


    private String name;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
}
