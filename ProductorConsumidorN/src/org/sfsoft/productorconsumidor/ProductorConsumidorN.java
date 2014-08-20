package org.sfsoft.productorconsumidor;

/**
 * Ejemplo Productor-Consumidor con varios productores y consumidores
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class ProductorConsumidorN {

	// Número de productores/consumidores
	private final static int LIMITE = 5;
	// Tamaño de la cola
	private final static int TAMANO = 100;
	
	public static void main(String[] args) {

		Cola cola = new Cola(TAMANO);
		Productor[] productores = new Productor[LIMITE];
		Consumidor[] consumidores = new Consumidor[LIMITE];
		
		// Lanza los consumidores
		for (int i = 0; i < LIMITE; i++) {
			consumidores[i] = new Consumidor(cola);
			consumidores[i].start();
		}
		
		// Lanza los productores
		for (int i = 0; i < LIMITE; i++) {
			productores[i] = new Productor(cola) ;
			productores[i].start();
		}
		
		for (int i = 0; i < LIMITE; i++) {
			try {
				// Espera que los productores terminen
				productores[i].join();
			} catch (InterruptedException ie) {}
		}
		
		for (int i = 0; i < LIMITE; i++) {
			// Añade un elemento que marque el final para que el consumidor termine
			cola.anadir(-1);
		}
	
		// Espera que los consumidores terminen
		for (int i = 0; i < LIMITE; i++) {
			try {
				consumidores[i].join();
			} catch (InterruptedException ie) {}
		}
		
		// Todos los hilos han terminado su ejecución
		System.out.println("Fin Programa");
	}
}
