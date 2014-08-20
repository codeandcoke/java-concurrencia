package org.sfsoft.hilos_compartir;

import java.util.ArrayList;

/**
 * Clase que representa un recurso de datos al que varios hilos 
 * deben acceder de forma concurrente
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class Recurso {

	private ArrayList<String> datos;
	
	/**
	 * Constructor
	 * @param datos del recurso compartido
	 */
	public Recurso(ArrayList<String> datos) {
		this.datos = datos;
	}
	
	/**
	 * Saca el último dato disponible del recurso compartido
	 * El método se marca como sincronizado para que solamente un hilo al mismo tiempo pueda invocarlo
	 * 
	 * Simulamos que obtener el dato conlleva cierto tiempo de procesamiento (con un sleep) y de esa manera podemos
	 * comprobar la diferencia entre hacer el método sincronizado y no hacerlo
	 * @return El dato que saca del recurso
	 */
	public synchronized String utilizar() throws InterruptedException {
		
		String dato = null;
		
		if (datos.size() > 0) {
		
			Thread.sleep(200);
			dato = datos.remove(datos.size() - 1);
		}
		
		return dato;
	}

}
