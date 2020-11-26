package com.example.demo.repository;
import com.example.demo.model.Libro;
import com.example.demo.model.Escritor;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EscritorRepository extends JpaRepository<Escritor, Integer> {
	//Page<Escritor> findByPostId(Integer postId, Pageable pageable);
}