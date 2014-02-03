package org.sfsoft.examenpsp1;

/**
* @author Santiago Faci
*/
public class Ring {

	private int golpes;
	
	public Ring() {
		golpes = 0;
	}
	
	public int getGolpes() {
		return golpes;
	}
	
	public synchronized void pegar(Boxeador boxeador) {
		
		boxeador.pegar();
		golpes++;
		
		System.out.println("Pegada de " + boxeador.getNombre() + " (" + boxeador.getGolpes() + ")");
	}
}
