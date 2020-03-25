package com.alura.microservice.loja.DTO;

import lombok.Data;

@Data
public class InfoFornecedorDTO {

    private Long id;

    private String nome;
    private String estado;
    private String endereco;
}
