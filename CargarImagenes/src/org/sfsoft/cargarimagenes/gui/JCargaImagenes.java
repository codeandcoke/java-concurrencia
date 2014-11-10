package org.sfsoft.cargarimagenes.gui;

import org.sfsoft.cargarimagenes.tasks.TareaCargaImagen;
import org.sfsoft.cargarimagenes.util.FiltroImagen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Aplicación que muestra como trabajar con la clase SwingWorker para la
 * ejecución de tareas en segundo plano y actualización de GUIs 'thread-safe'
 *
 * En este caso se realiza la carga de unas imágenes en segundo plano, que luego
 * la tarea lanzada devolverá como valor de retorno. A medida que se vaya realizando
 * la carga se irá mostrando el progreso en una barra de progreso y se notificará
 * en la barra de estado
 *
 * @author Santiago Faci
 * @version curso 2014-2015*
 */
public class JCargaImagenes {
    private JPanel panel1;
    private JButton btExaminar;
    private JTextField tfRuta;
    private JButton btCargar;
    private JButton btCancelar;
    private JLabel lbEstado;
    private JProgressBar pbCarga;

    // Listado de ficheros cuyas imágenes deben cargarse
    private ArrayList<File> ficheros;
    /* Listado de imágenes cargadas.
     * En esta variable se recogería el valor cargado por la tarea lanzada
     * en segundo plano
     */
    private ArrayList<BufferedImage> imagenes;
    /*
     * La tarea
     */
    private TareaCargaImagen tarea;

    public JCargaImagenes() {
        btCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargar();
            }
        });


        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });


        btExaminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                examinar();
            }
        });
    }

    /*
	 * Método invocado cuando el usuario pulsar en el botón Examinar
	 * Aquí se selecciona el directorio cuyas imágenes se van a cargar
	 */
    private void examinar() {

        /*
		 * Se crea y se muestra un diálogo de selección de directorios
		 */
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            return;

		/*
		 *  Se recoge el directorio seleccionado y se listan todos los ficheros de imágenes
		 */

        File directorio = fileChooser.getSelectedFile();
        tfRuta.setText(directorio.getAbsolutePath());
        ficheros = new ArrayList<File>();
        ficheros.addAll(Arrays.asList(directorio.listFiles(new FiltroImagen())));

        if (ficheros.size() == 0)
            JOptionPane.showMessageDialog(null, "El directorio no contiene imágenes", "Examinar", JOptionPane.WARNING_MESSAGE);

        btCargar.setEnabled(true);
    }

    /*
	 * Método invocado cuando el usuario pulsa el botón de cargar
	 * Se lanza la tarea en segundo plano
	 */
    private void cargar() {

        /*
		 *  Crea la tarea en segundo plano
		 *
		 *  Además, se define un listener de cambios sobre la barra de
		 *  progreso que será notificada a través de la tarea lanzada
		 *
		 *  Después, se lanza la tarea
		 */
        tarea = new TareaCargaImagen(ficheros, lbEstado);
        tarea.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getPropertyName().equals("progress")) {
                    pbCarga.setValue((Integer) event.getNewValue());
                }
            }
        });
        tarea.execute();

        btCancelar.setEnabled(true);
        btCargar.setEnabled(false);

		/*
		 * Con la llamada a tarea.get() se obtienen los resultados devueltos
		 * por la tarea, en este caso la lista de imágenes cargadas
		 * Hay que tener en cuenta que la llamada a ese método bloquea la ejecución
		 * del interfaz si se hace directamente desde el hilo principal de la aplicación
		 */
    }

    /*
	 * Método invocado cuando el usuario pulsa el botón cancelar
	 * Se cancela la tarea lanzada en segundo plano
	 */
    private void cancelar() {

        btCargar.setEnabled(true);
        btCancelar.setEnabled(false);

		/*
		 * Si la tarea se cancela con éxito se vacia la barra de progreso
		 * Si la tarea no 'da facilidades' para su cancelación, ésta no se
		 * llevara a cabo
		 */
        if (tarea.cancel(true))
            pbCarga.setValue(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JCargaImagenes");
        frame.setContentPane(new JCargaImagenes().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
