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
	private Boxeador rival;
	private boolean noqueado;
	
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
		
		rival.setNoqueado(true);
	}
	
	public synchronized void setNoqueado(boolean noqueado) {
		this.noqueado = noqueado;
	}
	
	public void setRival(Boxeador rival) {
		this.rival = rival;
	}
	
	public Boxeador getRival() {
		return rival;
	}
	
	public void noquear() {
		
	}
	
	@Override
	public void run() {
		
		while (ring.getGolpes() < 100) {
			
			if (noqueado) {
				try {
					Thread.sleep(new Random().nextInt(250));
				} catch (InterruptedException ie) {}
				noqueado = false;
			}
			else {
				ring.pegar(this);
				try {
					Thread.sleep(new Random().nextInt(500));
				} catch (InterruptedException ie) {}
			}
		}
	}
}
