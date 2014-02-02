package org.sfsoft.patronobserver.base;

import java.util.Observable;
import java.util.Observer;

/**
 * Clase Producto
 * Es la clase Observable. Los cambios que se produzcan ser�n 
 * observados inmediatamente por las clases Observer que se registren
 * desde �sta
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Producto extends Observable {

	private String nombre;
	private String descripcion;
	private float precio;
	private int stock;
	
	/*
	 *  �nico observador
	 *  En el caso de que hubiera varios, podr�an guardarse
	 *  como colecci�n
	 */
	private Observer observer;
	
	public Producto() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
		
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
		notifyObservers();
	}
	
	/**
	 * M�todo que permite a�adir observadores de esta clase
	 */
	@Override
	public void addObserver(Observer observer) {
		this.observer = observer;
	}
	
	/**
	 * M�todo que notifica los cambios a los observadores
	 * de esta clase
	 */
	@Override
	public void notifyObservers() {
		if (observer != null)
			observer.update(this, "stock");
	}
}
