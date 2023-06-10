package uy.edu.um.prog2.adt.tads.Heap;

public interface MyHeap<K, T> {
    void insert (K key, T data);
    T extractMax ();
    int size();
    boolean is_empty();

    // heapifyUp: sube el elemento ingresado hasta la posición correcta,
    // comparando padres e hijos para así mantener la propiedad de ordenación.
    void heapifyUp();

    // heapifyDown: baja el elemento raíz hasta la posición correcta para mantener la propiedad de ordenación.
    void heapifyDown();
}
