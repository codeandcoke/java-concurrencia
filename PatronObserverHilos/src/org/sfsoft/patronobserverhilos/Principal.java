package org.sfsoft.patronobserverhilos;

import org.sfsoft.patronobserverhilos.base.TareaObservable;
import org.sfsoft.patronobserverhilos.base.TareaObserver;

/**
 * Ejemplo que muestra c�mo se implementa el patr�n Observer
 * utilizando el API que Java proporciona en una aplicaci�n
 * con Hilos
 * 
 * En este caso, disponemos de un Hilo de forma que es �l mismo quien 
 * notifica los cambios que realiza a otra clase
 * 
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Principal {

	public static void main(String args[]) {
		
		TareaObserver observador = new TareaObserver();
		TareaObservable observable = new TareaObservable();
		observable.addObserver(observador);

		Thread hilo = new Thread(observable);
		hilo.start();
	}
}
