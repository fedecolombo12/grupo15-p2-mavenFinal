package uy.edu.um.prog2.adt.tads.Hash;

import uy.edu.um.prog2.adt.entities.User;

public interface MyHash<K, V>{
    public void put(K key, V value);
    V get(K key);
    public boolean contains(K key);
    public void remove(K key);
    int size();
    boolean isEmpty();
    void printHash();

}