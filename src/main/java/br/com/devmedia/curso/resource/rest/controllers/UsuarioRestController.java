package br.com.devmedia.curso.resource.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devmedia.curso.entities.Usuario;
import br.com.devmedia.curso.services.UsuarioInterfaceService;

//A Anotação RestController irá transformar nosso controller, em um controller do tipo REST. Ele recebe solicitações rest e tambêm resebe instruções
@RestController
@RequestMapping(value = "usuarios", 
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioRestController {

    @Autowired
    private UsuarioInterfaceService service;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listar(){
        return service.getTodos();
    }

}