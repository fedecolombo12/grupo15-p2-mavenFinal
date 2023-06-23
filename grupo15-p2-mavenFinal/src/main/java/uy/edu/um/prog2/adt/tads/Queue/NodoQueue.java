package uy.edu.um.prog2.adt.tads.Queue;

public class NodoQueue<T> {
    private T value;
    private int prioridad;
    private NodoQueue<T> siguiente;

    public NodoQueue(T value) {
        this.value = value;
    }

    public NodoQueue(T value, int prioridad) {
        this.value = value;
        this.prioridad = prioridad;
        siguiente = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodoQueue<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoQueue<T> siguiente) {
        this.siguiente = siguiente;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}

