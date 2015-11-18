package org.sfsoft.descargaficheros.gui;

import org.sfsoft.descargaficheros.tareas.Descarga;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 * Aplicación que realiza la descarga de un fichero en segundo plano
 * mientras refresca el interfaz con una barra de progreso mostrando
 * el proceso de la descarga
 * @author Santiago Faci
 * @version curso 2015-2016
 */

public class JDescargaFichero {
    private JPanel panel1;
    private JTextField tfUrl;
    private JButton btDescarga;
    private JProgressBar pbDescarga;

    public JDescargaFichero() {
        btDescarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descargarFichero();
            }
        });
    }

    private void descargarFichero() {

        Descarga descarga = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        File rutaFichero = fileChooser.getSelectedFile();

        try {

            descarga = new Descarga(tfUrl.getText(), rutaFichero.getAbsolutePath());
            descarga.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent event) {
                    if (event.getPropertyName().equals("progress")) {
                        pbDescarga.setValue((Integer) event.getNewValue());
                    }
                }
            });
            descarga.execute();

        } catch (Exception e) {
            if (e instanceof MalformedURLException)
                JOptionPane.showMessageDialog(null, "La URL no es correcta", "Descargar Fichero", JOptionPane.ERROR_MESSAGE);
            else if (e instanceof FileNotFoundException)
                JOptionPane.showMessageDialog(null, "No se ha podido leer el fichero origen", "Descargar Fichero", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No se ha podido leer el fichero origen", "Descargar Fichero", JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("JDescargaFichero");
        frame.setContentPane(new JDescargaFichero().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
