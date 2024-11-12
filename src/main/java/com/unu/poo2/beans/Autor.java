package com.unu.poo2.beans;

public class Autor {
	
	private int idAutor;
	private String nombre;
	private String nacionalidad;
	
	public Autor(int idAutor, String nombre, String nacionalidad) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	public Autor () {
		this(0,"","");
	}
	
	public Object[] getObject() {
		return new Object[] {
				this.idAutor,
				this.nombre,
				this.nacionalidad
		};
	}
	
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	

}
