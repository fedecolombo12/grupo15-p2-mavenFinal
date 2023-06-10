package uy.edu.um.prog2.adt.tads.Queue;
import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;

public class MyQueueImpl <T> implements MyQueue<T> {
    private NodoQueue<T> primero;
    private NodoQueue<T> ultimo;
    public MyQueueImpl() {
        this.primero = null;
        this.ultimo = null;
    }
    public NodoQueue<T> getPrimero() {
        return primero;
    }
    public void setPrimero(NodoQueue<T> primero) {
        this.primero = primero;
    }
    public NodoQueue<T> getUltimo() {
        return ultimo;
    }
    public void setUltimo(NodoQueue<T> ultimo) {
        this.ultimo = ultimo;
    }
    @Override
    public void enqueue(T element) {
        NodoQueue<T> newNodo = new NodoQueue<>(element);
        if (isEmpty()) {
            setPrimero(newNodo);
            setUltimo(newNodo);
        } else {
            newNodo.setSiguiente(getPrimero());
            setPrimero(newNodo);
        }
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        T item;
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            item = getUltimo().getValue();
            NodoQueue<T> aux = getPrimero();
            if (aux.getSiguiente() == null) { // un elemento solo
                setPrimero(null);
                setUltimo(null);
            } else {
                while (aux.getSiguiente() != getUltimo()) {
                    aux = aux.getSiguiente();
                }
                setUltimo(aux);
                aux.setSiguiente(null);
            }
        }
        return item;
    }
    @Override
    public boolean isEmpty() {
        return getPrimero() == null && getUltimo() == null;
    }

    @Override
    public void imprimirQueue() {
        NodoQueue<T> aux = getPrimero();
        while (aux.getSiguiente() != null) {
            System.out.print(aux.getValue() + ", ");
            aux = aux.getSiguiente();
        }
        System.out.println(aux.getValue());
    }

    @Override
    public void enqueueRight(T element) {
        NodoQueue<T> newNodo = new NodoQueue<>(element);
        if (isEmpty()) {
            setPrimero(newNodo);
            setUltimo(newNodo);
        } else {
            getUltimo().setSiguiente(newNodo);
            newNodo.setSiguiente(null);
            setUltimo(newNodo);
        }
    }

    @Override
    public T dequeueLeft() throws EmptyQueueException {
        T item;
        if (isEmpty()) {
            throw new EmptyQueueException();
        } else {
            item = getPrimero().getValue();
            setPrimero(getPrimero().getSiguiente());
        }
        return item;
    }

}

