package org.sfsoft.patronobserverhilos;

import org.sfsoft.patronobserverhilos.base.TareaObservable;
import org.sfsoft.patronobserverhilos.base.TareaObserver;

/**
 * Ejemplo que muestra cómo se implementa el patrón Observer
 * utilizando el API que Java proporciona en una aplicación
 * con Hilos
 * 
 * En este caso, disponemos de un Hilo de forma que es él mismo quien
 * notifica los cambios que realiza a otra clase
 * 
 * @author Santiago Faci
 * @version curso 2014-2015
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
