
package org.sfsoft.holahilo;

/**
 * Clase principal
 * Lanzamos dos hilos y comprobamos como se ejecutan de forma concurrente
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class HolaHiloThread {

	public static void main(String args[]) {
	
		Hilo hilo1 = new Hilo();
		Hilo hilo2 = new Hilo();
		
		// También se pueden crear hilos declarando un objeto Thead e implementando el método run "al vuelo"
		Thread hilo3 = new Thread(){
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						
						System.out.println("Hola! soy el hilo " + this.getName());
						Thread.sleep(500);
					}
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		};
		
		// Lanza la ejecución de los dos hilos
		// Una vez que se lanza un hilo, su ejecución pasa a segundo plano
		hilo1.start();
		hilo2.start();
		hilo3.start();
	}
}
