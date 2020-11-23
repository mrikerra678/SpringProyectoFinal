package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Escritor;
import com.example.demo.repository.EscritorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@Transactional
public class EscritorService {
	  @Autowired
	    private EscritorRepository escritorRepository;
	    public List<Escritor> listAllEscritor() {
	        return escritorRepository.findAll();
	    }

	    public void saveEscritor(Escritor escritor) {
	    	escritorRepository.save(escritor);
	    }

	    public Escritor getEscritor(Integer id) {
	        return escritorRepository.findById(id).get();
	    }

	    public void deleteEscritor(Integer id) {
	    	escritorRepository.deleteById(id);
	    }
}