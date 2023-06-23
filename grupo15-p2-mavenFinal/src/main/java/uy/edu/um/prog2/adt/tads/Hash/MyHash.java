package uy.edu.um.prog2.adt.tads.Hash;

public interface MyHash<K, T> {
    void put(K key, T value);

    NodoHash<K, T> getNode(K key);

    T get(K key);

    boolean contains(K key);

    void remove(K key);

    int size();

    boolean isEmpty();

    void printHash();

}