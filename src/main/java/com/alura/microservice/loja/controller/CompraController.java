package com.alura.microservice.loja.controller;

import com.alura.microservice.loja.DTO.CompraDTO;
import com.alura.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/compra")
public class CompraController implements Serializable {

    @Autowired
    private CompraService service;

    @PostMapping
    public void realizaCompra(@RequestBody CompraDTO compra){
        service.realizaCompra(compra);
    }
}
