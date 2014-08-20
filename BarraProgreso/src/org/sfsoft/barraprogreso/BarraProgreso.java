package org.sfsoft.barraprogreso;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

/**
 * Aplicación que muestra como trabajar con hilos y el control JProgressBar para diseñar GUIs
 * Se muestra como lanzar una tarea en segundo plano y cómo es posible cancelarla desde el GUI
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class BarraProgreso extends JFrame {

	private JPanel contentPane;
	private JProgressBar pbCarga;
	private JButton btCargar;
	private JButton btDetener;
	
	// Se utiliza para controlar si la carga se debe continuar o no 
	private boolean cargando;

	/*
	 * El usuario ha hecho click en el botón de cargar
	 */
	private void cargar() {
		
		Thread hiloCarga = new Thread(){
			public void run() {
				
				// Se activa la carga
				cargando = true;
				// Mientras la barra de progreso no esté completa y no se haya cancelado la carga, ésta se realiza
				while (pbCarga.getValue() < 100 && cargando) {
					pbCarga.setValue(pbCarga.getValue() + 5);
					try {
						// Una pausa que simula la tarea que haya que realizar
						Thread.sleep(500);
					} catch (InterruptedException ie) {}
				}
				
				// La carga ha terminado, se muestra un mensaje
				if (cargando)
					JOptionPane.showMessageDialog(null, "Carga completada", "Carga", JOptionPane.INFORMATION_MESSAGE);
				// El usuario ha cancelado la carga
				else {
					pbCarga.setValue(0);
					JOptionPane.showMessageDialog(null, "Carga cancelada por el usuario", "Carga", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		hiloCarga.start();
	}
	
	/*
	 * El usuario cancela la carga
	 */
	private void detener() {
		
		cargando = false;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// Crea la ventana y la hace visible
		BarraProgreso ventana = new BarraProgreso();
		ventana.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public BarraProgreso() {
		setSize(new Dimension(445, 308));
		
		setTitle("Barra Progreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Coloca la ventana en el centro de la pantalla
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pbCarga = new JProgressBar();
		pbCarga.setBounds(22, 239, 407, 20);
		contentPane.add(pbCarga);
		
		btCargar = new JButton("Cargar");
		// Método que atiende el evento click sobre el botón de carga. Se invoca al método que la realiza
		btCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargar();
			}
		});
		btCargar.setBounds(68, 144, 117, 29);
		contentPane.add(btCargar);
		
		btDetener = new JButton("Detener");
		// Método que atiende el evento click sobre el botón que detiene la carga. Se invoca al método que lo realiza
		btDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detener();
			}
		});
		btDetener.setBounds(247, 144, 117, 29);
		contentPane.add(btDetener);
	}
}
