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

import com.example.demo.model.Escritor;
import com.example.demo.service.EscritorService;

@RestController
@RequestMapping("/Escritores")
public class EscritorController {
	@Autowired
    EscritorService escritorService;

    @GetMapping("")
    public List<Escritor> list() {
        return escritorService.listAllEscritor();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escritor> get(@PathVariable Integer id) {
        try {
            Escritor escritor = escritorService.getEscritor(id);
            return new ResponseEntity<Escritor>(escritor, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Escritor>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Escritor escritor) {
    	escritorService.saveEscritor(escritor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Escritor escritor, @PathVariable Integer id) {
        try {
        	Escritor existEscritor = escritorService.getEscritor(id);
        	escritor.setIdEscritor(id);
        	escritorService.saveEscritor(escritor);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

    	escritorService.deleteEscritor(id);
    }
}