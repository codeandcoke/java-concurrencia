package org.sfsoft.hilos_sincronizar;

/**
 * Clase que muestra como sincronizar dos hilos de forma
 * que un hilo se ejecute mientras dure la ejecución de un primer hilo
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Hilos_Sincronizar {

	/*
	 * Lanza los dos hilos.
	 * hilo2 espera a que termine hilo1 para empezar
	 * También muestra el tiempo en el que ambos hilos terminan, que tendrá que coincidir
	 */
	public static void main(String[] args) {
			
		Hilo1 hilo1 = new Hilo1();
		Hilo2 hilo2 = new Hilo2(hilo1);
		long tiempo = 0;
		
		// Almacena el instante de tiempo en que se lanzan los hilos
		tiempo = System.currentTimeMillis();
		hilo1.start();
		hilo2.start();
		
		// El hilo principal espera a que terminen los dos hilos, que lo harán al mismo tiempo
		// puesto que hilo2 se ejecuta mientras hilo1 lo haga
		
		try {
			hilo1.join();
			// Muestra el tiempo pasado desde que se inició el hilo
			System.out.println("Fin hilo1: " + ((System.currentTimeMillis() - tiempo)) + " ms");
			
			hilo2.join();
		} catch (InterruptedException ie) {}
		
		// Muestra el tiempo pasado desde que se inició el hilo
		System.out.println("Fin hilo2: " + ((System.currentTimeMillis() - tiempo)) + " ms");
	}
}
