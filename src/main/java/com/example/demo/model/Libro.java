package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;



import java.util.List;


/**
 * The persistent class for the libros database table.
 *
 */
@Entity
@Table(name="libros")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_libro")
	private int idLibro;

	private String editorial;

	private String titulo;

	//bi-directional many-to-one association to Escritor
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id_escritor")
	private Escritor escritor;

	//bi-directional many-to-many association to Cliente
	@ManyToMany(mappedBy="libros", cascade=CascadeType.ALL)
	private List<Cliente> clientes;

	public Libro() {
	}

	public int getIdLibro() {
		return this.idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getEditorial() {
		return this.editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Escritor getEscritor() {
		return this.escritor;
	}

	public void setEscritor(Escritor escritor) {
		this.escritor = escritor;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}