package uy.edu.um.prog2.adt.tads.Stack;

import uy.edu.um.prog2.adt.exceptions.EmptyStackException;

public class MyStackImpl<T> implements MyStack<T>{
    public MyStackImpl() {
        this.ultimo = ultimo;
    }
    private NodoStack<T> ultimo;

    public NodoStack<T> getUltimo() {
        return ultimo;
    }
    public void setUltimo(NodoStack<T> ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T valor = getUltimo().getValue();
            setUltimo(getUltimo().getSiguiente());
            /* Nodo<T> aux = getUltimo().getSiguiente();
            setUltimo(aux); OTRA FORMA*/
            return valor;
        }
    }
    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return getUltimo().getValue();
    }

    @Override
    public void push(T element) {
        NodoStack<T> new_nodo = new NodoStack<>(element);
        if (isEmpty()) {
            setUltimo(new_nodo);
        } else {
            NodoStack<T> aux = getUltimo();
            new_nodo.setSiguiente(aux);
            setUltimo(new_nodo);
        }
    }

    @Override
    public void imprimirStack() {
        NodoStack<T> nodoActual = getUltimo();
        while (nodoActual != null) {
            System.out.println(nodoActual.getValue());
            nodoActual = nodoActual.getSiguiente();
        }
    }

    @Override
    public boolean isEmpty() {
        return getUltimo() == null;
    }

    @Override
    public void makeEmpty() throws EmptyStackException {
        while (!isEmpty()) {
            pop();
        }
    }
}

