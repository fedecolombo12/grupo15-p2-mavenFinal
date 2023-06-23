package uy.edu.um.prog2.adt.tads.Heap;

public class NodoHeap<K extends Comparable<K>, T> {
    private K key;

    private T value;

    public NodoHeap(K key, T data) {
        this.key = key;
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
}

