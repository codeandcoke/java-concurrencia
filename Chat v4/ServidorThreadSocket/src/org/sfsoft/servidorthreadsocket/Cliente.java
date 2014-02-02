package org.sfsoft.servidorthreadsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Gestiona la comunicación con cada uno de los clientes
 * conectados al servidor
 * @author Santiago Faci
 *
 */
public class Cliente extends Thread {

	private Socket socket;
	private PrintWriter salida;
	private BufferedReader entrada;
	private Servidor servidor;
	private String nick;
	
	public Cliente(Socket socket, Servidor servidor) throws IOException {
		this.socket = socket;
		this.servidor = servidor;
		
		salida = new PrintWriter(socket.getOutputStream(), true);
		entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public PrintWriter getSalida() {
		return salida;
	}
	
	public void cambiarNick(String nick) {
		this.nick = nick;
	}
	
	public String getNick() {
		return nick;
	}
	
	@Override
	public void run() {
		System.out.println("Iniciando comunicación con el cliente");
		
		// Envía algunos mensajes al cliente en cuanto éste se conecta
		salida.println("/server Hola " + socket.getInetAddress().getHostName());
		salida.println("/server Escribe tu nick y pulsa enter");
		try {
			String nick = entrada.readLine();
			cambiarNick(nick);
			salida.println("/server Bienvenido " + nick);
			salida.println("/server Hay " + servidor.getNumeroClientes() + " usuarios conectados");
			salida.println("/server Cuando escribas '/quit', se terminará la conexión");
		
			servidor.enviarNicks();
		
			String linea = null;
			/*
			 * Espera la entrada por parte del cliente y actúa según
			 * su protocolo: Repetir los mensajes y si el cliente
			 * envía el caracter . salir
			 */
			while ((linea = entrada.readLine()) != null) {
				
				if (linea.equals("/quit")) {
					salida.println("/server Saliendo . . .");
					// Cierra la conexión con el cliente
					socket.close();
					servidor.eliminarCliente(this);
					break;
				}
				
				servidor.enviarATodos("/users " + nick + " " + linea);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
