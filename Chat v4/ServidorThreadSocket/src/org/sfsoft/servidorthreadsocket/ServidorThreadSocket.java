
package org.sfsoft.servidorthreadsocket;

import java.io.IOException;

/**
 * Ejemplo de Servidor con soporte para múltiples cliente
 * utilizando hilos
 * 
 * @author Santiago Faci
 *
 */
public class ServidorThreadSocket {

	public static final int PUERTO = 5000;
	
	public static void main(String args[]) {
		
		Servidor servidor = new Servidor(PUERTO);
		Cliente cliente = null;
		
		try {
			// Inicia el servidor
			servidor.conectar();
			// Mientras el servidor esté conectado aceptas nuevas
			// conexiones de clientes, que serán atendidas a través
			// de hilos
			while (servidor.estaConectado()) {
				cliente = new Cliente(servidor.escuchar(),
					servidor);
				servidor.anadirCliente(cliente);
				System.out.println("Nuevo cliente conectado");
				cliente.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
