package uy.edu.um.prog2.adt.tads.Queue;

public class NodoQueue<T> {
    private T value;
    private NodoQueue<T> siguiente;
    public NodoQueue(T value) {
        this.value = value;
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
}

