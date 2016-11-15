package org.sfaci.uidinamica.gui;

import org.sfaci.uidinamica.tasks.Tarea;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Panel que aparece para representar cada una de las tareas que ejecuta la aplicación
 *
 * @author Santiago Faci
 * @version curso 2015-2016
 */
public class PanelWorker {
    private JProgressBar pbProgreso;
    JPanel panel1;
    private JButton btParar;
    private JLabel lbEstado;

    private Tarea tarea;
    private String nombre;
    private int velocidad;

    public PanelWorker(String nombre, int velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
    }

    public void iniciar() {
        tarea = new Tarea(nombre, velocidad);
        tarea.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("progress")) {
                    pbProgreso.setValue((Integer) evt.getNewValue());
                }
            }
        });
        tarea.execute();
    }
}
