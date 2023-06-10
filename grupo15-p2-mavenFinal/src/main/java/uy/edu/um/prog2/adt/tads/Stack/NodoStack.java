package uy.edu.um.prog2.adt.tads.Stack;

public class NodoStack<T> {
    private T value;
    private NodoStack<T> siguiente;
    private NodoStack<T> anterior;
    private int cont;
    public NodoStack(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public NodoStack<T> getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoStack<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoStack<T> getAnterior() { return anterior;}

    public void setAnterior(NodoStack<T> anterior) { this.anterior = anterior; }
}
