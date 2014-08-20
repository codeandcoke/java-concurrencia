package org.sfsoft.hilos_compartir;

import java.util.ArrayList;

/**
 * Clase que muestra como compartir un recurso entre dos hilos
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Hilos_Compartir {

	private final static int CANTIDAD = 20;
	
	/*
	 * Lanza varios hilos que utilizan información de un mismo recurso compartido
	 */
	public static void main(String[] args) {
		
		ArrayList<String> datos = new ArrayList<String>();
		Recurso recurso = new Recurso(datos);
		Hilo hilo1 = new Hilo(recurso);
		Hilo hilo2 = new Hilo(recurso);
		Hilo hilo3 = new Hilo(recurso);
		Hilo hilo4 = new Hilo(recurso);
		
		// Genera datos para el recurso compartido
		for (int i = 0; i < CANTIDAD; i++) {
			datos.add(String.valueOf(i));
		}
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		
	}
}
