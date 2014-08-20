package org.sfsoft.productorconsumidor;

import java.util.ArrayList;

/**
 * Clase que representa una cola de elementos
 * En este ejemplo será lo que se conoce como un recurso compartido, puesto que varios hilos accederán de forma
 * concurrente a ella
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Cola {

	private int limite;
	private ArrayList<Integer> datos;
	
	public Cola(int limite) {
		
		this.limite = limite;
		datos = new ArrayList<Integer>(limite);
	}
	
	/**
	 * Operación que añade un elemento a la cola
	 * El método se declara como sincronizado para que Java nos asegure que nunca lo ejecuta más de un hilo al mismo tiempo
	 * @param elemento El elemento que se quiere añadir
	 */
	public synchronized void anadir(int elemento) {
		
		// Si la cola está llena el hilo (productor) esperará (wait)
		while (datos.size() == limite) {
			try {
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
		datos.add(elemento);
		
		// Notifica a los hilos que esperan que pueden continuar
		notifyAll();
	}
	
	/**
	 * Operación que elimina un elemento de la cola
	 * El método se declara como sincronizado para que Java nos asegure que nunca lo ejecuta más de un hilo al mismo tiempo
	 * @return El elemento eliminado
	 */
	public synchronized Integer eliminar() {
		
		// Si la cola está vacía el hilo (consumidor) espera (wait)
		while (datos.size() == 0) {
			try {
				wait();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
		Integer elemento = datos.remove(datos.size() - 1);
		// Notifica a los hilos que esperan que pueden continuar
		notifyAll();
		
		return elemento;
	}
}
