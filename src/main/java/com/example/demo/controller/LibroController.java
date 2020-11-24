package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Libro;
import com.example.demo.repository.EscritorRepository;
import com.example.demo.service.LibroService;

@RestController
@RequestMapping("/Libros")
public class LibroController {
	@Autowired
	LibroService libroService;
	@Autowired
	EscritorRepository escritorRepository;

    @GetMapping("")
    public List<Libro> list() {
        return libroService.listAllLibro();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> get(@PathVariable Integer id) {
        try {
        	Libro libro = libroService.getLibro(id);
            return new ResponseEntity<Libro>(libro, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/escritor")
    public void add(@PathVariable(value = "id")Integer id,  @RequestBody Libro libro) {
    	return escritorRepository.findById(id).map(escritor->{
    		libro.setEscritor(escritor);
    		libroService.saveLibro(libro);
    	});
    	System.out.print("PUTA MIERDA");

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Libro libro, @PathVariable Integer id) {
        try {
        	Libro existLibro = libroService.getLibro(id);
        	libro.setIdLibro(id);
        	libroService.saveLibro(libro);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

    	libroService.deleteLibro(id);
    }
}