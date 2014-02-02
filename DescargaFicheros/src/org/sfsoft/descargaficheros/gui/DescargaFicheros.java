package org.sfsoft.descargaficheros.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.sfsoft.descargarficheros.tareas.Descarga;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Aplicación que realiza la descarga de un fichero en segundo plano
 * mientras refresca el interfaz con una barra de progreso mostrando
 * el proceso de la descarga
 * @author Santiago Faci
 *
 */
public class DescargaFicheros {

	private JFrame frame;
	private JTextField tfUrl;
	private JButton btDescarga;
	private JProgressBar pbDescarga;

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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescargaFicheros window = new DescargaFicheros();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DescargaFicheros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 298, 186);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(115dlu;default)"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		frame.getContentPane().add(getTfUrl(), "6, 6, fill, default");
		frame.getContentPane().add(getBtDescarga(), "6, 8");
		frame.getContentPane().add(getPbDescarga(), "6, 10");
	}

	public JTextField getTfUrl() {
		if (tfUrl == null) {
			tfUrl = new JTextField();
			tfUrl.setText("http://media-cyber.law.harvard.edu/blogs/gems/ion/DavidBravoCopiaestelibro.zip");
			tfUrl.setColumns(10);
		}
		return tfUrl;
	}
	public JButton getBtDescarga() {
		if (btDescarga == null) {
			btDescarga = new JButton("Descargar");
			btDescarga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					descargarFichero();
				}
			});
		}
		return btDescarga;
	}
	public JProgressBar getPbDescarga() {
		if (pbDescarga == null) {
			pbDescarga = new JProgressBar();
		}
		return pbDescarga;
	}
}
