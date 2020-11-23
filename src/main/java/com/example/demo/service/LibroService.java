package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import javax.transaction.Transactional;
@Service
@Transactional
public class LibroService {
	 @Autowired
	    private LibroRepository libroRepository;
	    public List<Libro> listAllLibro() {
	        return libroRepository.findAll();
	    }

	    public void saveLibro(Libro libro) {
	    	libroRepository.save(libro);
	    }

	    public Libro getLibro(Integer id) {
	        return libroRepository.findById(id).get();
	    }

	    public void deleteLibro(Integer id) {
	    	libroRepository.deleteById(id);
	    }
}