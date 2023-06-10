package uy.edu.um.prog2.adt.tads.Lista;

public class NodoLista<T>{
    private T value;

    private NodoLista<T> siguiente;

    private int priority;

    public NodoLista(T value) {
        this.value = value;
        this.siguiente = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> next) {
        this.siguiente = next;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
