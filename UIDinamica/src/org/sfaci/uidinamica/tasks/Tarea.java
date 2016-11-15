package org.sfaci.uidinamica.tasks;

import javax.swing.*;

/**
 * Created by mto on 13/11/15.
 */
public class Tarea extends SwingWorker<Void, Integer> {

    private String nombre;
    private int velocidad;
    private int distanciaRecorrida;

    public Tarea(String nombre, int velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
    }

    @Override
    protected Void doInBackground() throws Exception {

        while (distanciaRecorrida < 100) {
            distanciaRecorrida += velocidad;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {}

            setProgress(distanciaRecorrida);
        }

        distanciaRecorrida = 100;

        return null;
    }
}
