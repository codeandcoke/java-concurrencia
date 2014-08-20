package org.sfsoft.holahilo2;

/**
 * Clase que define la tarea que ejecutará el hilo
 * En este caso implementamos la clase implementando el interfaz Runnable
 * Utilizaremos esta manera de crear hilos cuando nuestra clase ya esté heredando (por necesidades de diseño) de otra clase
 * @author Santiago Faci
 * @version 2014-2015
 */
public class TareaRunnable implements Runnable {
	
	private String nombre;
	private static int MAXIMO = 10;
	
	public TareaRunnable(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * En este método se define la tarea que realizará el hilo
	 * En este caso mostrará un mensaje un número determinado de veces  
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		try {
			for (int i = 0; i < MAXIMO; i++) {
				
				System.out.println("Hola! soy el hilo " + nombre);
				Thread.sleep(500);
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}

