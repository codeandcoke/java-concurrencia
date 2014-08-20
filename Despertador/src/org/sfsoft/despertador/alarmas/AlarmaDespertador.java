package org.sfsoft.despertador.alarmas;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import org.sfsoft.despertador.util.Util;

/**
 * Clase que crea una tarea en segundo plano que interactúa con un GUI
 * de forma 'thread-safe'
 * 
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class AlarmaDespertador extends SwingWorker<Void, Void> {

	private int hora;
	private int minuto;
	private boolean activada;
	private JLabel lbEstado;
	
	/**
	 * Constructor
	 * @param lbEstado Etiqueta del GUI que actualiza esta tarea en segundo plano
	 * @param hora hora de la alarma
	 * @param minuto minuto de la arlama
	 */
	public AlarmaDespertador(JLabel lbEstado, int hora, int minuto) {
		
		this.lbEstado = lbEstado;
		this.hora = hora;
		this.minuto = minuto;
		activada = true;
	}
	
	/**
	 * Desactiva la alarma
	 */
	public void desactivar() {
		activada = false;
	}
	
	/**
	 * Hace sonar la alarma. Actualiza el GUI con un mensaje
	 */
	public void sonar() {
		
		lbEstado.setText("Despierta!!!!!!");
		desactivar();
	}
	
	/**
	 * Método donde se define la tarea en segundo plano que se debe realizar
	 */
	@Override
	protected Void doInBackground() throws Exception {
		
		int[] momentoActual = new int[2];
		
		/*
		 * Comprueba si tiene que sonar mientras esté activada y no se haya terminado la tarea (isDone)
		 */
		while ((activada) && (!isDone())) {
			
			System.out.println("Comprobando hora");
			
			momentoActual = Util.getHoraMinutoActual();
			if ((momentoActual[0] == hora) && (momentoActual[1] == minuto)) {
				sonar();
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {}
		}
		
		if (isCancelled())
			lbEstado.setText("Alarma cancelada por el usuario");
		
		return null;
	}

}
