package org.sfsoft.servidorthreadsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Representa al servidor echo
 * @author Santiago Faci
 *
 */
public class Servidor {

	private int puerto;
	private ServerSocket socket;
	
	private ArrayList<Cliente> clientes;
	
	public Servidor(int puerto) {
		this.puerto = puerto;
		clientes = new ArrayList<Cliente>();
	}
	
	public void anadirCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void eliminarCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	
	public void enviarATodos(String mensaje) {
		
		for (Cliente cliente : clientes) {
			cliente.getSalida().println(mensaje);
		}
	}
	
	/**
	 * Envia la lista de nicks a todos los clientes
	 */
	public void enviarNicks() {
		
		for (Cliente cliente : clientes) {
			cliente.getSalida().println(obtenerNicks());
		}
	}
	
	/**
	 * Obtiene la lista de nicks separados por comas
	 * @return
	 */
	public String obtenerNicks() {
	
		String nicks = "/nicks,";
		
		for (Cliente cliente : clientes) {
			nicks += cliente.getNick() + ",";
		}
		
		return nicks;
	}
	
	public int getNumeroClientes() {
		return clientes.size();
	}
	
	/**
	 * Indica si el servidor está conectado
	 * @return
	 */
	public boolean estaConectado() {
		return !socket.isClosed();
	}
	
	/**
	 * Inicia el servidor
	 */
	public void conectar() throws IOException {
		socket = new ServerSocket(puerto);
	}
	
	/**
	 * Desconecta el servidor
	 * @throws IOException
	 */
	public void desconectar() throws IOException {
		socket.close();
	}
	
	/**
	 * Escucha la conexión de un cliente
	 * @return El socket de conexión con el cliente
	 * @throws IOException
	 */
	public Socket escuchar() throws IOException {
		return socket.accept();
	}
}
