package org.sfsoft.examenpsp1;

import java.util.Random;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Principal {

	public static void main(String args[]) {
	
		int n = 20;
		Random generador = new Random();
		Boxeador[] boxeadores = new Boxeador[n];
		Ring ring = new Ring();
		
		// Crea los boxeadores
		for (int i = 0; i < n; i++)
			boxeadores[i] = new Boxeador(String.valueOf(i), ring);
		
		// Se les asigna rival (puede acabar peg�ndose a si mismo)
		for (int i = 0; i < n; i++)
			boxeadores[i].setRival(boxeadores[generador.nextInt(n - 1)]);
		
		// Inicia el combate
		for (int i = 0; i < n; i++)
			boxeadores[i].start();
		
		// Espera que llegue a su fin
		for (int i = 0; i < n; i++) {
			try {
				boxeadores[i].join();
			} catch (InterruptedException ie) {}
		}
		
		System.out.println("Fin del combate");
		
		// Calcula el boxeador con m�s golpes (no respeta empates)
		int golpesGanadores = 0;
		String ganador = null;
		for (Boxeador boxeador : boxeadores) {
			if (boxeador.getGolpes() > golpesGanadores) {
				ganador = boxeador.getNombre();
				golpesGanadores = boxeador.getGolpes();
			}
		}
		
		System.out.println("El ganador es: " + ganador);
	}
}
