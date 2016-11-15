package com.sfaci.ejemplolog4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ejemplo sobre cómo utilizar log4j para crear ficheros de registro
 *
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class OtraClase {

    private static final Logger logger = LogManager.getLogger(OtraClase.class);

    public void unMetodo() {

        logger.trace("Se ha ejecutado el método unMetodo");
    }
}
