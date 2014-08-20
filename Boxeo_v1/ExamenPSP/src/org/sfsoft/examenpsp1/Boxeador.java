package org.sfsoft.examenpsp1;

import java.util.Random;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Boxeador extends Thread {

	private String nombre;
	private Ring ring;
	private int golpes;
	
	public Boxeador(String nombre, Ring ring) {
		
		this.nombre = nombre;
		this.ring = ring;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getGolpes() {
		return golpes;
	}
	
	public void pegar() {
		golpes++;
	}
	
	@Override
	public void run() {
		
		while (ring.getGolpes() < 100) {
			
			ring.pegar(this);
			try {
				Thread.sleep(new Random().nextInt(500));
			} catch (InterruptedException ie) {}
		}
	}
}
