package org.sfsoft.carreracoches;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * Representa a cada uno de los coches que compiten en la carrera
 * Notifican, mediante la clase SwingWorker su progreso al UI de la clase principal
 * @author Santiago Faci
 * @version curso 2014-2015
*/
public class Coche extends SwingWorker<Void, Integer> {

	private int velocidad;
	private int distanciaCarrera;
	private int distanciaRecorrida;
	private JLabel marcador;
	private String nombre;

	public Coche(int velocidad, int distanciaCarrera,
		JLabel marcador, String nombre) {
		
		this.velocidad = velocidad;
		this.distanciaCarrera = distanciaCarrera;
		distanciaRecorrida = 0;
		this.marcador = marcador;
		this.nombre = nombre;
	}

    public Coche(int distanciaCarrera,
                 JLabel marcador, String nombre) {

        this.velocidad = new Random().nextInt(30) + 5;
        this.distanciaCarrera = distanciaCarrera;
        distanciaRecorrida = 0;
        this.marcador = marcador;
        this.nombre = nombre;
    }

	@Override
	protected Void doInBackground() throws Exception {
		
		while (distanciaRecorrida < distanciaCarrera) {
			Thread.sleep(100);
			distanciaRecorrida += velocidad;
			if (distanciaRecorrida > distanciaCarrera)
				distanciaRecorrida = distanciaCarrera;
			
			setProgress(distanciaRecorrida * 100 / 
				distanciaCarrera);
			
			/*if (isCancelled())
				return null;*/
		}
		
		marcador.setText(nombre + ": He ganado");
		firePropertyChange("ganador", "", nombre);
		
		return null;
	}
}
