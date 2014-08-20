package org.sfsoft.cargarimagenes.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Clase que implementa un filtro aplicable sobre ficheros
 * En este caso es un filtro para ficheros de imagen
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class FiltroImagen implements FileFilter{

	@Override
	public boolean accept(File fichero) {
		
		if (fichero.getName().endsWith(".jpg"))
			return true;
		
		if (fichero.getName().endsWith(".png"))
			return true;
		
		if (fichero.getName().endsWith(".gif"))
			return true;
		
		return false;
	}

}
