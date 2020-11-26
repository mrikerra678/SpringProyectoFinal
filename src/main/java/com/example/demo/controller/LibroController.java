package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Cliente;
import com.example.demo.model.Escritor;
import com.example.demo.model.Libro;
import com.example.demo.repository.EscritorRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.LibroService;

@RestController
@RequestMapping("/Libros")
public class LibroController {
	
	LibroService libroService;
	private final LibroRepository libroRepository;
	EscritorRepository escritorRepository;
	@Autowired
	public LibroController(LibroRepository libroRepository, EscritorRepository escritorRepository) {
		this.libroRepository = libroRepository;
		this.escritorRepository = escritorRepository;
	}
	
	
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
    
    //http://localhost:8080/Libros/nuevo?titulo=asa&editorial=usa&escritor=1
    @RequestMapping("/nuevo")
    public void insertarLibro( 
                @RequestParam(value = "titulo", defaultValue = "sin_nombre") String nombre,
                 @RequestParam(value = "editorial" , defaultValue = "1000") String editorial,
                 @RequestParam(value = "escritor", defaultValue = "10") int escritor) {

      Libro libro = new Libro();
      libro.setTitulo(nombre);
      libro.setEditorial(editorial);
      libro.setEscritor(escritorRepository.findById(escritor).orElse(null));
      libroRepository.save(libro);
    }
   
    //http://localhost:8080/Libros/modificar?id=1&titulo=asa2&editorial=usa2&escritor=1
    @RequestMapping("/modificar")
    public void modificarEscritor( 
                @RequestParam(value = "id", defaultValue = "0") int id,
                @RequestParam(value = "titulo", defaultValue = "sin_nombre") String nombre,
                @RequestParam(value = "editorial" , defaultValue = "editorial") String editorial,
                @RequestParam(value = "escritor", defaultValue = "0") int escritor) {

      Libro libro = new Libro();
      libro = libroRepository.findById(id).get();
      libro.setTitulo(nombre);
      libro.setEditorial(editorial);
      libro.setEscritor(escritorRepository.findById(escritor).orElse(null));
      libroRepository.save(libro);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

    	libroService.deleteLibro(id);
    }
}