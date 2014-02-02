package org.sfsoft.clientechat.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JConecta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField tfServidor;

	private String servidor;
	
	public enum Accion {
		ACEPTAR, CANCELAR;
	}
	private Accion accion;
	
	public String getServidor() {
		return servidor;
	}
	
	private void cancelar() {
	
		accion = Accion.CANCELAR;
		setVisible(false);
	}
	
	private void aceptar() {
		
		accion = Accion.ACEPTAR;
		servidor = tfServidor.getText();
		setVisible(false);
	}
	
	public Accion mostrarDialogo() {
		
		setVisible(true);
		
		return accion;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JConecta dialog = new JConecta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JConecta() {
		setTitle("Conecta");
		setModal(true);
		setBounds(100, 100, 177, 108);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			tfServidor = new JTextField();
			contentPanel.add(tfServidor);
			tfServidor.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
