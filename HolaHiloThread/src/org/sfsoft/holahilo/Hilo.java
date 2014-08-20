package org.sfsoft.holahilo;

/**
 * Clase que define el hilo y la tarea que éste realiza
 * En este caso implementamos el hilo creando una clase que hereda de la clase Thread
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Hilo extends Thread {
	
	private static int MAXIMO = 10;
	
	/**
	 * En este método se define la tarea que realizará el hilo
	 * En este caso mostrará un mensaje un número determinado de veces  
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		try {
			for (int i = 0; i < MAXIMO; i++) {
				
				System.out.println("Hola! soy el hilo " + this.getName());
				Thread.sleep(500);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
