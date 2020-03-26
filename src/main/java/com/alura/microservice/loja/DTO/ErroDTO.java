package com.alura.microservice.loja.DTO;

import lombok.Data;

@Data
public class ErroDTO {
	
	private String code;
	private String error;
	private String message;

}

