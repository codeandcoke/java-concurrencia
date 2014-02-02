package org.sfsoft.patronobserver;

import java.util.GregorianCalendar;

import org.sfsoft.patronobserver.base.Cliente;
import org.sfsoft.patronobserver.base.Producto;

/**
 * Ejemplo que muestra cómo se implementa el patrón Observer
 * utilizando el API que Java proporciona
 * 
 * En este caso, disponemos de un Cliente que reacciona
 * ante el cambio en el stock de un producto
 * 
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class Principal {

	public static void main(String args[]) {
		
		Producto producto = new Producto();
		producto.setNombre("Patatas");
		producto.setDescripcion("Patatas fritas");
		producto.setPrecio(1.20f);
		producto.setStock(0);
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("CLI0001");
		cliente.setNombre("Un");
		cliente.setApellidos("tipo");
		cliente.setEmail("un@tipo.com");
		cliente.setFechaNacimiento(new GregorianCalendar().getTime());
		
		/*
		 * Se añaden observadores a la clase observable
		 */
		producto.addObserver(cliente);
		/*
		 * Un cambio en la clase observada hará "reaccionar" a la clase observadora
		 */
		producto.setStock(23);
	}
}
