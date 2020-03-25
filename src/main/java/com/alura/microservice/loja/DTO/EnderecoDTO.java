package com.alura.microservice.loja.DTO;

import lombok.Data;

@Data
public class EnderecoDTO {

    private String rua;
    private Integer numero;
    private String estado;
}
