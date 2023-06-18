package uy.edu.um.prog2.adt.tads.Hash;

import uy.edu.um.prog2.adt.entities.User;

public class TablaHash<K, V> implements MyHash<K, V> {

    private ListaHash<K,V>[] buckets;
    int size;


    public TablaHash(int size) {
        this.size = size;
        buckets =  new ListaHash[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ListaHash<>();
        }
    }

    public int hashCode(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % size;
    }

    @Override
    public void put(K key, V value) {
        int code = hashCode(key);
        ListaHash<K, V> bucket = buckets[code];
        if (contains(key)) {
            NodoHash<K, V> existingNode = bucket.getNode(key);
            existingNode.setData(value); // Actualiza el valor existente
        } else {
            bucket.add(new NodoHash<>(key, value)); // Agrega un nuevo nodo si la clave no existe
        }
    }
    @Override
    public boolean contains(K key) {
        int code = hashCode(key);
        ListaHash<K, V> bucket = buckets[code];
        NodoHash<K, V> current = bucket.getFirst();
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }



    @Override
    public V get(K key) {
        int code = hashCode(key);
        return buckets[code].get(key);
    }


    public void printHash() {
        for (int i = 0; i < size; i++) {
            System.out.print("Bucket " + i + ": ");
            ListaHash<K, V> bucket = buckets[i];
            bucket.print();
            System.out.println();
        }
    }


    @Override
    public void remove(K key) {
        int code = hashCode(key);
        NodoHash<K,V> cleannode = buckets[code].getNode(key);
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

    public ListaHash<K, V>[] getBuckets(int index) throws Exception {
        if (index < 0 || index >= size){
            throw new Exception();
        }
        return new ListaHash[]{buckets[index]};
    }

    public void setBuckets(ListaHash<K, V>[] buckets) {
        this.buckets = buckets;
    }
}