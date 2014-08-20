package org.sfsoft.examenpsp1;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Principal {

	public static void main(String args[]) {
	
		Ring ring = new Ring();
		Boxeador boxeador1 = new Boxeador("Tyson", ring);
		Boxeador boxeador2 = new Boxeador("Rocky", ring);
		
		boxeador1.setRival(boxeador2);
		boxeador2.setRival(boxeador1);
		
		boxeador1.start();
		boxeador2.start();
		
		try {
			boxeador1.join();
			boxeador2.join();
		} catch (InterruptedException ie) {}
		
		System.out.println("Fin del combate");
		if (boxeador1.getGolpes() > boxeador2.getGolpes())
			System.out.println("Ha ganado " + boxeador1.getNombre());
		else if (boxeador1.getGolpes() < boxeador2.getGolpes())
			System.out.println("Ha ganado " + boxeador2.getNombre());
		else
			System.out.println("Empate");
	}
}
