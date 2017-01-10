package com.sfaci.aviones;

import com.sfaci.aviones.base.Avion;
import com.sfaci.aviones.base.AvionPerseguidor;

import java.util.Scanner;

/**
 * Clase Principal
 */
public class Principal {

    public static void main(String args[]) {

        AvionPerseguidor perseguidor = new AvionPerseguidor(2000, 200, Avion.Direccion.IZQUIERDA);
        Avion avion = new Avion(1000, 100, Avion.Direccion.DERECHA);
        avion.addObserver(perseguidor);

        /* Para cada cambio que hace el avión se podrá comprobar en pantalla
         * cómo ha ido reaccionando el avión perseguidor
         */
        avion.subir();
        avion.bajar();
        avion.bajar();
        avion.girar(Avion.Direccion.IZQUIERDA);
        avion.girar(Avion.Direccion.DERECHA);
        avion.acelerar();

        System.out.println();
        System.out.println("Altura perseguidor " + perseguidor.getAltura());
        System.out.println("Velocidad perseguidor " + perseguidor.getVelocidad());
        System.out.println("Dirección perseguidor " + perseguidor.getDireccion().toString());
    }
}
