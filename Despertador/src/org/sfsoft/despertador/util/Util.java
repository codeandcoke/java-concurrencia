package org.sfsoft.despertador.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase con métodos
 * 
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Util {

	/**
	 * Devuelve la hora y minuto actual
	 * @return Un vector con la hora en la posición 0 y los minutos en la posición 1
	 */
	public static int[] getHoraMinutoActual() {
		
		int horaMinuto[] = new int[2];
		Calendar calendario = new GregorianCalendar();
		
		horaMinuto[0] = calendario.get(Calendar.HOUR_OF_DAY);
		horaMinuto[1] = calendario.get(Calendar.MINUTE);
		
		return horaMinuto;
	}
}
