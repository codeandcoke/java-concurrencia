package org.sfsoft.patronobserverhilos.base;

import java.util.Observable;
import java.util.Observer;

/**
 * Clase TareaObserver
 * En este caso es la clase que observa (Observer)
 * Observa los cambios que se produzcan en otra clase, a la que se conoce como Observable
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class TareaObserver implements Runnable, Observer {

	private int valor;
	private boolean funcionando;
	
	public TareaObserver() {
		
		funcionando = true;
		valor = 0;
	}
	
	public void terminar() {
		funcionando = false;
	}

	@Override
	public void run() {
		
		while (funcionando) {
			
			
		}
	}
	
	/**
	 * M�todo que se ejecuta cuando se producen cambios
	 * en la clase observada
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		if (((String) arg).equals("valor"))
			System.out.println("El hilo observable ha modificado el valor");
	}
}
