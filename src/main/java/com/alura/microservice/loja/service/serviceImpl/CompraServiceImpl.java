package com.alura.microservice.loja.service.serviceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alura.microservice.loja.DTO.CompraDTO;
import com.alura.microservice.loja.DTO.ResultadoDTO;
import com.alura.microservice.loja.service.CompraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
//        ResponseEntity<InfoFornecedorDTO> exchange =
//          client.exchange("http://fornecedor/info/"+ compra.getEndereco().getEstado(),
//          HttpMethod.GET,null,InfoFornecedorDTO.class);
//        System.out.print(exchange.getBody().getEndereco());
//        
                
        /**
         * Acessando servico sem esta inscrico no eureka
         */
//        RestTemplate client1 = new RestTemplate();
//        ResponseEntity<AreaDTO> exchange1 =
//        		client1.exchange("http://brq-api.herokuapp.com/api/v1/areas/1",
//                HttpMethod.GET,null,AreaDTO.class);
//        System.out.print("==================RETORNO: "+  exchange1.getBody().getNome());
        
        
        String token = "9df0bf776a2193c8cc3297a76847c32a83dbb7f6";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));        
        headers.add("User-Agent", "Spring's RestTemplate" );  // value can be whatever
        headers.add("Authorization", "Bearer "+token );
        
        RestTemplate client2 = new RestTemplate();
        ResponseEntity<ResultadoDTO> exchange2 =
        		client2.exchange("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token="+token,
        				HttpMethod.GET,null,ResultadoDTO.class);
//                HttpMethod.GET,new HttpEntity<>("parameters", headers),ErroDTO.class);
        System.out.print("==================RETORNO: "+  exchange2.getBody().getCifrado());
        
        ObjectMapper mapper = new ObjectMapper();
        
        String resultado = "";
        ResultadoDTO dto;
		try {
			resultado = mapper.writeValueAsString(exchange2.getBody());
			System.out.println(resultado);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {

            dto = mapper.readValue(resultado, new TypeReference<ResultadoDTO>(){});
            System.out.println(dto);
        	

        } catch (IOException e) {
            e.printStackTrace();
       }
    }
    
    
    
 
}
