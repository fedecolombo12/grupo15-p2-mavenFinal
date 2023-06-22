package uy.edu.um.prog2.adt.tads.Hash;

import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

public class TablaHash<K, T extends Comparable<T>> implements MyHash<K, T> {

    private ListaHash<K, T>[] buckets;
    int size;

    public TablaHash(int size) {
        this.size = size;
        buckets =  new ListaHash[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ListaHash<>();
        }
    }

    public ListaHash<K, T>[] getBuckets() {
        return buckets;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int hashCode(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    @Override
    public void put(K key, T value) {
        int code = hashCode(key);
        ListaHash<K, T> bucket = buckets[code];
        if (contains(key)) {
            NodoHash<K, T> existingNode = bucket.getNode(key);
            existingNode.setData(value); // Actualiza el valor existente
        } else {
            bucket.add(new NodoHash<>(key, value)); // Agrega un nuevo nodo si la clave no existe
        }
    }
    @Override
    public boolean contains(K key) {
        int code = hashCode(key);
        ListaHash<K, T> bucket = buckets[code];
        NodoHash<K, T> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public T get(K key) {
        int code = hashCode(key);
        return buckets[code].get(key);
    }

    @Override
    public NodoHash<K,T> getNode(K key){
        int code = hashCode(key);
        return buckets[code].getNode(key);
    }

    public void printHash() {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            ListaHash<K, T> bucket = buckets[i];
            bucket.print();
            System.out.println();
        }
    }


    @Override
    public void remove(K key) {
        int code = hashCode(key);
        NodoHash<K, T> cleannode = buckets[code].getNode(key);
        buckets[code].remove(cleannode);
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < buckets.length; i++) {
            count += buckets[i].size();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public ListaEnlazada<NodoHash<K,T>> getAllEntries(){
        ListaEnlazada<NodoHash<K,T>> entries = new ListaEnlazada<>();

        for (ListaHash<K,T> bucket : buckets) {
            NodoHash<K,T> current = bucket.getFirst();
            while (current != null){
                entries.add(current);
                current = current.getNext();
            }
        }
        return entries;
    }

    public ListaHash[] getBuckets(int index) {
        if (index < 0 || index >= size){
            return new ListaHash[0];
        }
        return new ListaHash[]{buckets[index]};
    }

    public void setBuckets(ListaHash<K, T>[] buckets) {
        this.buckets = buckets;
    }
}