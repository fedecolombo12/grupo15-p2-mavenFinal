package uy.edu.um.prog2.adt.tads.Queue;
import uy.edu.um.prog2.adt.exceptions.EmptyQueueException;

public interface MyQueue<T> {
    void enqueue (T element); //sirve para enqueueLeft
    T dequeue () throws EmptyQueueException; // sirve para dequeueRight
    boolean isEmpty();
    void imprimirQueue();
    void enqueueRight(T element);
    T dequeueLeft () throws EmptyQueueException;
}

