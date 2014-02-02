package org.sfsoft.cargarimagenes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

import org.sfsoft.cargarimagenes.tasks.TareaCargaImagen;
import org.sfsoft.cargarimagenes.util.FiltroImagen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingConstants;

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
 *
 */
public class CargarImagenes {

	private JFrame frmCargarImgenes;
	private JTextField tfRuta;
	private JButton btCargar;
	private JButton btExaminar;
	private JProgressBar pbCarga;
	private JLabel lbEstado;
	private JButton btCancelar;
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarImagenes window = new CargarImagenes();
					window.frmCargarImgenes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CargarImagenes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCargarImgenes = new JFrame();
		frmCargarImgenes.setTitle("Cargar Im\u00E1genes");
		frmCargarImgenes.setBounds(100, 100, 271, 252);
		frmCargarImgenes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCargarImgenes.setLocationRelativeTo(null);
		
		lbEstado = new JLabel("");
		lbEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		frmCargarImgenes.getContentPane().add(lbEstado, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frmCargarImgenes.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		tfRuta = new JTextField();
		panel.add(tfRuta, "4, 6, 9, 1, fill, default");
		tfRuta.setColumns(10);
		
		btExaminar = new JButton("Examinar");
		btExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				examinar();
			}
		});
		panel.add(btExaminar, "8, 8");
		
		btCargar = new JButton("Cargar");
		btCargar.setEnabled(false);
		btCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargar();
			}
		});
		panel.add(btCargar, "8, 10");
		
		pbCarga = new JProgressBar();
		panel.add(pbCarga, "4, 12, 9, 1");
		
		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btCancelar.setEnabled(false);
		panel.add(btCancelar, "8, 14");
	}

}
