package org.sfsoft.cargarimagenes.tasks;

import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
 * Tarea que realiza la carga de una serie de imágenes utilizando la clase SwingWorker,
 * devuelve la lista de imágenes cargadas y notifica el proceso de carga interactuando 
 * con la GUI
 * 
 * En este caso, los parámetros de tipo que se pasan a SwingWorker indican que se devolverá
 * una lista de imágenes (ArrayList<BufferedImage>) al invocar al método get() desde el interfaz
 * y que los valores intermedios notificados al interfaz a través de los métodos publish() y process() 
 * son Integer. Esos valores servirán para notificar en cada momento la ejecución de la tarea
 * 
 * Además, mediante el método setProgress(int) se puede notificar el progreso (entre 0 y 100) en el
 * que se encuentra en cada momento la tarea
 * 
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class TareaCargaImagen extends SwingWorker<ArrayList<BufferedImage>, Integer>{
	
	private ArrayList<File> ficheros;
	private JLabel lbEstado;
	
	/**
	 * Constructor
	 * @param ficheros La lista de ficheros (imágenes) que se tiene que cargar
	 * @param lbEstado La etiqueta de texto donde notificar el proceso de carga
	 */
	public TareaCargaImagen(ArrayList<File> ficheros, JLabel lbEstado) {
		this.ficheros = ficheros;
		this.lbEstado = lbEstado;
	}

	/**
	 * Método abstracto heredado de la clase SwingWorker donde se realiza la 
	 * tarea en segundo plano.
	 * En este caso se cargan las imágenes una a una a la vez que se va notificando
	 * el proceso de carga para luego mostrarlo en una barra de progreso y en una
	 * etiqueta de texto
	 */
	@Override
	protected ArrayList<BufferedImage> doInBackground() throws Exception {
		
		ArrayList<BufferedImage> imagenes = new ArrayList<BufferedImage>();
		BufferedImage imagen = null;
		int cantidad = ficheros.size();
		int i = 1;
		
		for (File fichero : ficheros) {
			try {
				// Carga la imagen de disco a memoria
				imagen = ImageIO.read(fichero);
                imagenes.add(imagen);
				// Notifica el estado de la carga (en este caso el námero de imagen procesada)
				publish(i++);
				/*
				 *  Notifica el progreso (de 0 a 100). Aprovechamos para pintarlo en una barra de progreso
				 *  desde la GUI
				 */
				setProgress(100 * i / cantidad);
				
				/*
				 * Si la tarea ha sido cancelada se termina la carga de imágenes
				 * Si se desea que la tarea pueda ser cancelada en cualquier momento
				 * hay que 'dar facilidades' como comprobar periodicamente si se debe
				 * terminar su ejecución
				 */
				if (isCancelled())
					break;
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		// Al final del proceso se devuelven las imágenes cargadas
		return imagenes;
	}
	
	/**
	 * Método heredado de la clase SwingWorker donde se indican las tareas a realizar
	 * para actualizar el estado intermedio de la tarea en segundo plano. Los valores
	 * que desde el método doInBackground() notifiquemos invocando el método publish()
	 * se le pasan a este método en forma de lista puesto que no está asegurado que por 
	 * cada llamada a publish() haya una llamada a process(). Se pasarán siempre los
	 * valores publicados pendientes de procesar
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */
	@Override
    protected void process(List<Integer> valores) {
		
		lbEstado.setText(valores.get(valores.size() - 1) + " de " + ficheros.size());
    }
	
}
