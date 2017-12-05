package org.sfsoft.hilos_sincronizar;

/**
 * Clase que muestra como sincronizar dos hilos de forma
 * que un hilo espera a otro hilo para ejecutarse en orden
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Hilos_Sincronizar {

	/*
	 * Lanza los dos hilos.
	 * hilo2 espera a que termine hilo1 para empezar
	 */
	public static void main(String[] args) {
			
		Hilo1 hilo1 = new Hilo1();
		Hilo2 hilo2 = new Hilo2(hilo1);
		
		hilo1.start();
		hilo2.start();
	}
}
