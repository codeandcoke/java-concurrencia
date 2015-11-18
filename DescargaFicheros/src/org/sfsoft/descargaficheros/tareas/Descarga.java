package org.sfsoft.descargaficheros.tareas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.SwingWorker;

/**
 * Tarea SwingWorker que realiza la descarga de un fichero
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class Descarga extends SwingWorker<Void, Integer>{

	// La URL del fichero a descargar
	private String urlFichero;
	// La ruta del disco donde se descargará
	private String rutaFichero;
	
	public Descarga(String urlFichero, String rutaFichero) {
		this.urlFichero = urlFichero;
		this.rutaFichero = rutaFichero;
	}
	
	@Override
	protected Void doInBackground() throws MalformedURLException, FileNotFoundException, IOException {

		URL url = new URL(urlFichero);
		URLConnection conexion = url.openConnection();
		// Obtiene el tamaño del fichero en bytes
		int tamanoFichero = conexion.getContentLength();
	
		InputStream is = url.openStream();
		FileOutputStream fos = new FileOutputStream(rutaFichero);
		byte[] bytes = new byte[2048];
		int longitud = 0;
		int progresoDescarga = 0;
		
		// Descarga el fichero y va notificando el progreso
		while ((longitud = is.read(bytes)) != -1) {
			fos.write(bytes, 0, longitud);
			
			progresoDescarga += longitud;			
			setProgress((int) (progresoDescarga * 100 / tamanoFichero));
		}
		
		is.close();
		fos.close();
		
		setProgress(100);
		
		return null;
	}
}