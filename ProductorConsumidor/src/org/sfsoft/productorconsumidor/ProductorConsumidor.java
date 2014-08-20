package org.sfsoft.productorconsumidor;

/**
 * Ejemplo Productor-Consumidor con un productor y un consumidor
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class ProductorConsumidor {

	private static final int TAMANO = 100;
	
	public static void main(String[] args) {
		
		Cola cola = new Cola(TAMANO);
		Productor productor = new Productor(cola);
		Consumidor consumidor = new Consumidor(cola);
		
		// Lanzamos el productor y el consumidor
		productor.start();
		consumidor.start();
		
		try {
			// Esperamos que el productor termine
			productor.join();
		} catch (InterruptedException ie) {}
		
		// Añadimos un elemento que marque el final para que el consumidor termine
		cola.anadir(-1);
		
		try {
			consumidor.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		System.out.println("Fin Programa");
	}

}
