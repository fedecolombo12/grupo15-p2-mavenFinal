package uy.edu.um.prog2.adt.tads.Hash;

public interface MyHash<K, T>{
    public void put(K key, T value);
    NodoHash<K,T> getNode(K key);
    T get (K key);
    public boolean contains(K key);
    public void remove(K key);
    int size();
    boolean isEmpty();
    void printHash();

}