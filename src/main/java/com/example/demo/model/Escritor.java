package com.example.demo.model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the escritor database table.
 *
 */
@Entity
@Table(name="escritor")
@NamedQuery(name="Escritor.findAll", query="SELECT e FROM Escritor e")
public class Escritor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_escritor")
	private int idEscritor;

	private String apellido;

	private String nombre;

	//bi-directional many-to-one association to Libro
	@OneToMany(mappedBy="escritor", cascade={CascadeType.ALL})
	private List<Libro> libros;

	public Escritor() {
	}

	public int getIdEscritor() {
		return this.idEscritor;
	}

	public void setIdEscritor(int idEscritor) {
		this.idEscritor = idEscritor;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public Libro addLibro(Libro libro) {
		getLibros().add(libro);
		libro.setEscritor(this);

		return libro;
	}

	public Libro removeLibro(Libro libro) {
		getLibros().remove(libro);
		libro.setEscritor(null);

		return libro;
	}

}