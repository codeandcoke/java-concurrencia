package org.sfsoft.holahilo2;

/**
 * Clase principal
 * Lanzamos dos hilos y comprobamos como se ejecutan de forma concurrente
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class HolaHiloRunnable {

	public static void main(String args[]) {
		
		TareaRunnable tarea1 = new TareaRunnable("Hilo1");
		TareaRunnable tarea2 = new TareaRunnable("Hilo2");
		
		Thread hilo1 = new Thread(tarea1);
		Thread hilo2 = new Thread(tarea2);
		
		hilo1.start();
		hilo2.start();
	}
}
