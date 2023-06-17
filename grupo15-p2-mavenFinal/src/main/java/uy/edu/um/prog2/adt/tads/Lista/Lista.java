package uy.edu.um.prog2.adt.tads.Lista;

public interface Lista<T extends Comparable<T>> {
    void add(T value);
    void remove(T value);
    T get(int position);
    boolean contains(T value);
    int size();
    void addFirst(T value); // 3
    void change(T value, int direction); // ej 9
    /* ListaEnlazada<T> crearNuevaLista(); // 11 */
    void addOrder(T value);
    void imprimirLista();
    void quickSort();
    NodoLista<T> searchT (T value);
}

