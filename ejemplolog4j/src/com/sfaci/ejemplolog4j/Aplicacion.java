package com.sfaci.ejemplolog4j;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Ejemplo sobre cómo utilizar log4j para crear ficheros de registro
 *
 * En el fichero de configuración de log4j (log4j2.xml) se han configurado
 * dos appenders. Uno para la consola y otro para un fichero de texto
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class Aplicacion {

    private static final Logger logger = LogManager.getLogger(Aplicacion.class);

    public static void main(String args[]) {

        // Diferentes niveles de traza
        logger.trace("Aplicación iniciada");

        logger.error("Error de algo");

        logger.trace("Aplicación finalizada");

        logger.debug("Información para depurar");

        logger.warn("Esto es un aviso");

        OtraClase unObjeto = new OtraClase();
        unObjeto.unMetodo();

        try {
            // Forzamos una excepción para registrar su traza con log4j
            int x = 5 / 0;
        } catch (Exception e) {
            logger.trace("Se ha producido una excepción");

            // Almacena la traza de la excepción como String y lo registra con log4j
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error(sw.toString());
        }
    }
}
