package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Libro;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/Cliente")
public class ClienteController {
	@Autowired
    ClienteService clienteService;
	ClienteRepository clienteRepository;
	LibroRepository libroRepository;
	LibroService libroService;

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
    //http://localhost:8080/Cliente/nuevo?nombre=asa&apellido=usa&nLibrosPrestados=1&id_cliente=1&id_libro=1

    @RequestMapping("/nuevo")
    public void insertarCliente( 
                @RequestParam(value = "nombre", defaultValue = "sin_nombre") String nombre,
                @RequestParam(value = "apellido" , defaultValue = "1000") String apellido,
                @RequestParam(value = "nLibrosPrestados" , defaultValue = "1000") int nLibros,
                @RequestParam(value = "id_cliente", defaultValue = "10") int idcliente,
                @RequestParam(value = "id_libro", defaultValue = "10") int idlibro) {
      
    
     // List<Cliente> Clientes = new ArrayList<>();  
      Cliente cliente = new Cliente();
      cliente.setNombre(nombre);
      cliente.setApellido(apellido);
      cliente.setNLibrosPrestados(nLibros);
    //  Clientes.add(cliente);
      
     /* Libro libro = new Libro();
      libro=libroService.getLibro(idlibro);
      System.out.print(libro);
      libro = libroRepository.findById(idlibro).get();
      System.out.print(idlibro);
      libro.setClientes(Clientes);
      //cliente.setEscritor(escritorRepository.findById(escritor).orElse(null));
       * */
       
      clienteRepository.save(cliente);
     // libroRepository.save(libro);

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