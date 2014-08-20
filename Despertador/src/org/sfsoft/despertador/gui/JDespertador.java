package org.sfsoft.despertador.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.Sizes;
import javax.swing.JButton;

import org.sfsoft.despertador.alarmas.AlarmaDespertador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Aplicación que muestra como trabajar con la clase SwingWorker 
 * para lanzar tareas en segundo plano que actualizan un GUI
 * de forma 'thread-safe'
 * 
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class JDespertador {

	private JFrame frmDespertadorV;
	private JLabel lbEstado;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField tfHora;
	private JTextField tfMinuto;
	private JButton btActivar;
	private JButton btDesactivar;
	
	private AlarmaDespertador alarma;
	
	private void activarAlarma() {
		
		if (tfHora.getText().equals("") || tfMinuto.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Introduzca valores correctos", "Despertador", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/*
		 * Crea la tarea y lanza su ejecución
		 * A la hora de lanzarla le pasamos la referencia a los elementos del GUI con los que debe interactuar.
		 * De esa manera será Swing quién realizará, de forma 'thread-safe', la actualización de los componentes
		 * del GUI
		 */
		alarma = new AlarmaDespertador(lbEstado, Integer.parseInt(tfHora.getText()), Integer.parseInt(tfMinuto.getText()));
		alarma.execute();
		
		lbEstado.setText("Alarma activada");
		
		btDesactivar.setEnabled(true);
		btActivar.setEnabled(false);
		
	}
	
	private void desactivarAlarma() {
		
		/*
		 * Cancela la tarea en segundo plano indicando que puede ser interrumpida (true)
		 * En caso de que se le dejara terminar antes de detenerla se pasaría el valor false
		 */
		alarma.desactivar();
		alarma.cancel(true);
		
		btDesactivar.setEnabled(false);
		btActivar.setEnabled(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JDespertador window = new JDespertador();
					window.frmDespertadorV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JDespertador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDespertadorV = new JFrame();
		frmDespertadorV.setTitle("Despertador v1.0");
		frmDespertadorV.setBounds(100, 100, 259, 202);
		frmDespertadorV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbEstado = new JLabel("Alarma desactivada");
		frmDespertadorV.getContentPane().add(lbEstado, BorderLayout.SOUTH);
		
		panel = new JPanel();
		frmDespertadorV.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default"),
				FormFactory.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("39dlu", true), Sizes.constant("50dlu", true)), 0),},
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
		
		lblNewLabel = new JLabel("Hora");
		panel.add(lblNewLabel, "4, 6, left, default");
		
		tfHora = new JTextField();
		panel.add(tfHora, "6, 6, fill, default");
		tfHora.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Minuto");
		panel.add(lblNewLabel_1, "4, 8, left, default");
		
		tfMinuto = new JTextField();
		panel.add(tfMinuto, "6, 8, fill, default");
		tfMinuto.setColumns(10);
		
		btActivar = new JButton("Activar");
		btActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarAlarma();
			}
		});
		panel.add(btActivar, "4, 10");
		
		btDesactivar = new JButton("Desactivar");
		btDesactivar.setEnabled(false);
		btDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desactivarAlarma();
			}
		});
		panel.add(btDesactivar, "6, 10");
	}

}
