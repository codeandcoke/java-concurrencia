package org.sfsoft.clientechat.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JTextArea;

import org.sfsoft.clientechat.gui.JConecta.Accion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana {

	private JFrame frmClienteChat;
	private JScrollPane scrollPane;
	private JTextField tfMensaje;
	private JMenuBar menuBar;
	private JMenu mnChat;
	private JMenuItem mntmConectar;
	private JMenuItem mntmDesconectar;
	private JMenuItem mntmSalir;
	private JScrollPane scrollPane_1;
	private JList<String> listUsuarios;
	private JTextArea taChat;
	
	private Socket socket;
	private PrintWriter salida;
	private BufferedReader entrada;
	private boolean conectado;
	private static final int PUERTO = 5000;
	private DefaultListModel<String> modeloLista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmClienteChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
		inicializar();
	}
	
	private void inicializar() {
		modeloLista = new DefaultListModel<String>();
		listUsuarios.setModel(modeloLista);
	}
	
	private void conectar() {
		
		JConecta jConecta = new JConecta();
		
		if (jConecta.mostrarDialogo() == Accion.CANCELAR)
			return;
		
		String servidor = jConecta.getServidor();
		conectarServidor(servidor);
	}
	
	private void conectarServidor(String servidor) {
		
		try {
			socket = new Socket(servidor, PUERTO);
			salida = new PrintWriter(socket.getOutputStream(), true);
			entrada = 
					new BufferedReader(
					new InputStreamReader(
					socket.getInputStream()));
			conectado = true;
			
			Thread hiloRecibir = new Thread(new Runnable() {
				public void run() {
					while (conectado) {
						try {
							if (socket.isClosed()) {
								conectado = false;
								break;
							}
							String mensaje = entrada.readLine();
							if (mensaje == null)
								continue;
							
							int indice = 0;
							if (mensaje.startsWith("/server")) {
								indice = mensaje.indexOf(" ");			
								taChat.append("** " + mensaje.substring(indice + 1) + " **\n");
							}
							else if (mensaje.startsWith("/users")) {
								indice = mensaje.indexOf(" ", 7);
								String nick = mensaje.substring(7, indice);
								taChat.append("#" + nick + "# ");
								taChat.append(mensaje.substring(indice + 1) + "\n");
							}
							else if (mensaje.startsWith("/nicks")) {
								String[] nicks = mensaje.split(",");
								modeloLista.clear();
								for (int i = 1; i < nicks.length - 1; i++) {
									modeloLista.addElement(nicks[i]);
								}
							}
						} catch (SocketException se) {
							desconectar();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				}
			});
			hiloRecibir.start();
			
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void enviarMensaje() {
		
		String mensaje = tfMensaje.getText();
		salida.println(mensaje);
		
		tfMensaje.setText("");
	}
	
	private void desconectar() {
		try {
			salida.println("/quit");
			conectado = false;
			socket.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void salir() {
		System.exit(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClienteChat = new JFrame();
		frmClienteChat.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				desconectar();
				salir();
			}
		});
		frmClienteChat.setTitle("Cliente Chat");
		frmClienteChat.setBounds(100, 100, 450, 300);
		frmClienteChat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmClienteChat.getContentPane().add(getScrollPane(), BorderLayout.EAST);
		frmClienteChat.getContentPane().add(getTfMensaje(), BorderLayout.SOUTH);
		frmClienteChat.getContentPane().add(getScrollPane_1(), BorderLayout.CENTER);
		frmClienteChat.setJMenuBar(getMenuBar());
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(100, 2));
			scrollPane.setViewportView(getListUsuarios());
		}
		return scrollPane;
	}
	public JTextField getTfMensaje() {
		if (tfMensaje == null) {
			tfMensaje = new JTextField();
			tfMensaje.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER)
						enviarMensaje();
				}
			});
			tfMensaje.setColumns(10);
		}
		return tfMensaje;
	}
	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnChat());
		}
		return menuBar;
	}
	public JMenu getMnChat() {
		if (mnChat == null) {
			mnChat = new JMenu("Chat");
			mnChat.add(getMntmConectar());
			mnChat.add(getMntmDesconectar());
			mnChat.add(getMntmSalir());
		}
		return mnChat;
	}
	public JMenuItem getMntmConectar() {
		if (mntmConectar == null) {
			mntmConectar = new JMenuItem("Conectar");
			mntmConectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conectar();
				}
			});
		}
		return mntmConectar;
	}
	public JMenuItem getMntmDesconectar() {
		if (mntmDesconectar == null) {
			mntmDesconectar = new JMenuItem("Desconectar");
		}
		return mntmDesconectar;
	}
	public JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
		}
		return mntmSalir;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTaChat());
		}
		return scrollPane_1;
	}
	public JList<String> getListUsuarios() {
		if (listUsuarios == null) {
			listUsuarios = new JList<String>();
		}
		return listUsuarios;
	}
	public JTextArea getTaChat() {
		if (taChat == null) {
			taChat = new JTextArea();
		}
		return taChat;
	}
}
