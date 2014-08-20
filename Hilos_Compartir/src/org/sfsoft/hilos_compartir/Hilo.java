package org.sfsoft.hilos_compartir;


/**
 * Clase que representa a un hilo
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class Hilo extends Thread {

	private final static int MAXIMO = 10;
	private Recurso recurso;
	
	public Hilo(Recurso recurso) {
		this.recurso = recurso;
	}
	/*
	 * Tarea que desempeña el hilo. En este caso de forma simbólica 
	 * pinta una serie de líneas por pantalla
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		String dato = null;
		
		try {
			do {
				dato = recurso.utilizar();
				if (dato != null)
					System.out.println(getName() + " - " + dato);
			} while (dato != null);
		
		} catch (InterruptedException ie) {}
	}
}
