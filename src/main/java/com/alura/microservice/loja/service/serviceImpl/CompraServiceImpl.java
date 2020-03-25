package com.alura.microservice.loja.service.serviceImpl;

import com.alura.microservice.loja.DTO.CompraDTO;
import com.alura.microservice.loja.DTO.InfoFornecedorDTO;
import com.alura.microservice.loja.service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private RestTemplate client;
	
    @Override
    public void realizaCompra(CompraDTO compra) {

        /**
         * esse trecho de codigo esta acessando outro microservico
         */
        ResponseEntity<InfoFornecedorDTO> exchange =
          client.exchange("http://fornecedor/info/"+ compra.getEndereco().getEstado(),
          HttpMethod.GET,null,InfoFornecedorDTO.class);
        System.out.print(exchange.getBody().getEndereco());

    }
    
    
    
//    @Override
//    public void realizaCompra(CompraDTO compra) {
//
//        RestTemplate client = new RestTemplate();
//        /**
//         * esse trecho de codigo esta acessando outro microservico
//         */
//        ResponseEntity<InfoFornecedorDTO> exchange =
//        client.exchange("http://localhost:8081/info/"+ compra.getEndereco().getEstado(),
//                HttpMethod.GET,null,InfoFornecedorDTO.class);
//
//        System.out.print(exchange.getBody().getEndereco());
//
//    }
}
