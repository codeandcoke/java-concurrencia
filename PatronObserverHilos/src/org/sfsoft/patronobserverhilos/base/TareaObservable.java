package org.sfsoft.patronobserverhilos.base;

import java.util.Observable;
import java.util.Observer;

/**
 * Clase TareaObservable
 * Es la clase Observable. Los cambios que se produzcan ser�n 
 * observados inmediatamente por las clases Observer que se registren
 * desde �sta
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class TareaObservable extends Observable implements Runnable {

	private int valor;
	private boolean funcionando;
	
	/*
	 *  �nico observador
	 *  En el caso de que hubiera varios, podr�an guardarse
	 *  como colecci�n
	 */
	private Observer observer;
	
	public TareaObservable() {
		
		funcionando = true;
		valor = 0;
	}
	
	public void terminar() {
		
		funcionando = false;
	}

	@Override
	public void run() {
		
		while (funcionando) {
			
			valor++;
			notifyObservers();
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ie) {}
		}
	}
	
	/**
	 * M�todo que permite a�adir observadores de esta clase
	 */
	@Override
	public void addObserver(Observer observer) {
		this.observer = observer;
	}
	
	/**
	 * M�todo que notifica los cambios a los observadores
	 * de esta clase
	 */
	@Override
	public void notifyObservers() {
		if (observer != null)
			observer.update(this, "valor");
	}
}
