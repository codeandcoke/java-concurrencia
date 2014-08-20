package org.sfsoft.productorconsumidor;

/**
 * Clase que produce elementos y los añade en la cola
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Productor extends Thread {

	private Cola cola;
	
	public Productor(Cola cola) {
		this.cola = cola;
	}
	
	public void run() {
		
		for (int i = 0; i < 100; i++) {
			System.out.println("Productor " + getName() + " - " + i);
			cola.anadir(i);
		}
	}
}
