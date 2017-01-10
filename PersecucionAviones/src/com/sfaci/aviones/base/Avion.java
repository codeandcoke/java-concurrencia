package com.sfaci.aviones.base;

import java.util.Observable;
import java.util.Observer;

/**
 * Clase que representa a cualquier de los dos aviones
 * @author Santiago Faci
 * @version curso 2016-2017
 */
public class Avion extends Observable {

    public enum Direccion {
        DERECHA, IZQUIERDA;
    }

    public enum Accion {
        SUBIR, BAJAR, ACELERAR, FRENAR, GIRAR;
    }

    private int altura;
    private int velocidad;
    private Direccion direccion;

    private Observer observer;
    private Accion accion;

    public Avion(int altura, int velocidad, Direccion direccion) {
        this.altura = altura;
        this.velocidad = velocidad;
        this.direccion = direccion;
    }

    public int getAltura() {
        return altura;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void subir() {
        altura += 100;
        accion = Accion.SUBIR;
        notifyObservers();
    }

    public void bajar() {
        altura -= 100;
        accion = Accion.BAJAR;
        notifyObservers();
    }

    public void acelerar() {
        velocidad += 200;
        accion = Accion.ACELERAR;
        notifyObservers();
    }

    public void frenar() {
        velocidad -= 200;
        accion = Accion.FRENAR;
        notifyObservers();
    }

    public void girar(Direccion direccion) {
        this.direccion = direccion;
        accion = Accion.GIRAR;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            switch (accion) {
                case SUBIR:
                    observer.update(this, Accion.SUBIR);
                    break;
                case BAJAR:
                    observer.update(this, Accion.BAJAR);
                    break;
                case ACELERAR:
                    observer.update(this, Accion.ACELERAR);
                    break;
                case FRENAR:
                    observer.update(this, Accion.FRENAR);
                    break;
                case GIRAR:
                    observer.update(this, Accion.GIRAR);
                    break;
                default:
            }
        }

    }
}
