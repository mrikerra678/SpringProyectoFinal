package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;



import java.util.List;


/**
 * The persistent class for the cliente database table.
 *
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;

	private String apellido;

	private int nLibrosPrestados;

	private String nombre;

	//bi-directional many-to-many association to Libro

	@ManyToMany
	@JoinTable(
		name="ejemplares"
		, joinColumns={
			@JoinColumn(name="id_cliente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_libro")
			}
		)
	private List<Libro> libros;

	public Cliente() {
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getNLibrosPrestados() {
		return this.nLibrosPrestados;
	}

	public void setNLibrosPrestados(int nLibrosPrestados) {
		this.nLibrosPrestados = nLibrosPrestados;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}