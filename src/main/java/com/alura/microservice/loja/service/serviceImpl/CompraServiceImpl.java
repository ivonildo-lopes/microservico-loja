package com.alura.microservice.loja.service.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alura.microservice.loja.DTO.AreaDTO;
import com.alura.microservice.loja.DTO.CompraDTO;
import com.alura.microservice.loja.DTO.InfoFornecedorDTO;
import com.alura.microservice.loja.service.CompraService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private RestTemplate client;
	
    @Override
    public void realizaCompra(CompraDTO compra) {

        /**
         * esse trecho de codigo esta acessando outro microservico
         * inscrito no eureka
         */
        ResponseEntity<InfoFornecedorDTO> exchange =
          client.exchange("http://fornecedor/info/"+ compra.getEndereco().getEstado(),
          HttpMethod.GET,null,InfoFornecedorDTO.class);
        System.out.print(exchange.getBody().getEndereco());
        
                
        /**
         * Acessando servico sem esta inscrico no eureka
         */
        RestTemplate client1 = new RestTemplate();
        ResponseEntity<AreaDTO> exchange1 =
        		client1.exchange("http://brq-api.herokuapp.com/api/v1/areas/1",
                HttpMethod.GET,null,AreaDTO.class);
        AreaDTO dto = new AreaDTO();
        System.out.print("==================RETORNO: "+  exchange1.getBody().getNome());

    }
    
 
}
