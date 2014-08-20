package org.sfsoft.hilos_sincronizar;

/**
 * Clase que representa a un hilo que debe ejecutarse
 * de forma ininterrumpida mientras dure la ejecución de otro hilo
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class Hilo2 extends Thread {

	// Hilo al que deberá esperar
	private Thread hilo;
	
	public Hilo2(Thread hilo) {
		this.hilo = hilo;
	}

	/*
	 * Tarea que desempeña el hilo. En este caso de forma simbólica 
	 * pinta una serie de líneas por pantalla
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		try {
			while (hilo.isAlive()) {
				System.out.println("Hola, soy el hilo 2");
				Thread.sleep(100);
			}
		} catch (InterruptedException ie) {}
	}
}
