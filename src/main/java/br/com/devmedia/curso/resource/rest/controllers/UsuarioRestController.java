package br.com.devmedia.curso.resource.rest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    //COm a anotação @PostMapping estamos informando que esta metodo só irá efetuar requesicoes do tipo post
    /*O RequestBody faz com que já recebemos o proprio objeto informado, Assim já estamos recebendo o objeto usuario pronto 
    não parte por parte dele*/
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Usuario usuario){ 
        service.salvar(usuario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
 
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario getUsuario(@PathVariable("id") Long id){
        return service.getId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario editar(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        
        service.editar(id, usuario);

        return usuario;
    }

}