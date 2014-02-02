package org.sfsoft.productorconsumidor;

/**
 * Clase que consume elementos eliminándolos de la cola
 * @author Santiago Faci
 *
 */
public class Consumidor extends Thread {

	private Cola cola;
	
	public Consumidor(Cola cola) {
		
		this.cola = cola;
	}
	
	public void run() {
		
		Integer elemento;
		
		do {
			elemento = cola.eliminar();
			System.out.println("Consumidor " + getName() + " - " + elemento);
		} while (elemento != -1);
	}
}
