package com.alura.microservice.loja.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CompraDTO {

    private List<ProdutoDTO> itens;
    private EnderecoDTO endereco;
}
