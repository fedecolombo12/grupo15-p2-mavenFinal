package uy.edu.um.prog2.adt.tads.Hash;

public class NodoHash<K, T> implements Comparable<NodoHash<K, T>> {
    private K key;
    private T data;
    private NodoHash<K, T> next;

    public NodoHash(K key, T data) {
        this.key = key;
        this.data = data;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodoHash<K, T> getNext() {
        return next;
    }

    public void setNext(NodoHash<K, T> next) {
        this.next = next;
    }

    @Override
    public int compareTo(NodoHash<K, T> o) {
        return this.data.toString().compareTo(o.getData().toString());
    }
}
