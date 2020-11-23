package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/Cliente")
public class ClienteController {
	@Autowired
    ClienteService clienteService;

    @GetMapping("")
    public List<Cliente> list() {
        return clienteService.listAllCliente();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Integer id) {
        try {
            Cliente cliente = clienteService.getCliente(id);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Integer id) {
        try {
            Cliente existCliente = clienteService.getCliente(id);
            cliente.setIdCliente(id);
            clienteService.saveCliente(cliente);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

    	clienteService.deleteCliente(id);
    }
}