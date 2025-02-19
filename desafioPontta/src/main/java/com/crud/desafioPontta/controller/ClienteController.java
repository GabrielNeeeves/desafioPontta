package com.crud.desafioPontta.controller;

import com.crud.desafioPontta.model.ClienteDto;
import com.crud.desafioPontta.model.ClienteModel;
import com.crud.desafioPontta.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    //GET
    @GetMapping
    public List<ClienteModel> getClientes() {

        return repo.findAll();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ClienteModel byId(@PathVariable Long id) throws Exception {

        var clienteOptional = repo.findById(id);

        if(clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw  new Exception();
        }
    }

    //POST
    @PostMapping
    public ResponseEntity postCliente(@RequestBody ClienteDto dto) throws Exception {

        if(dto.email().contains("@")) {
            var novoCliente = new ClienteModel(dto);
            repo.save(novoCliente);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new Exception();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity putCliente(@PathVariable Long id, @RequestBody ClienteDto dto) {

        var clienteOptional = repo.findById(id);

        if(clienteOptional.isPresent()) {

            var clienteUpdate = clienteOptional.get();

            clienteUpdate.setEmail(dto.email());
            clienteUpdate.setPassword(dto.password());

            repo.save(clienteUpdate);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity delCliente(@PathVariable Long id) {

        Optional<ClienteModel> clienteOptional = repo.findById(id);

        if(clienteOptional.isPresent()) {
            var clienteDeleted = clienteOptional.get();
            repo.delete(clienteDeleted);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
