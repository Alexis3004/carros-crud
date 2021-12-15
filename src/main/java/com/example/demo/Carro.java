package com.example.demo;

public class Carro {
	private Long id;
	private String marca;
	private String nombre;

	public Carro() {
	}

	public Carro(Long id, String nombre, String marca) {
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setSalario(String marca) {
		this.marca = marca;
	}
}
