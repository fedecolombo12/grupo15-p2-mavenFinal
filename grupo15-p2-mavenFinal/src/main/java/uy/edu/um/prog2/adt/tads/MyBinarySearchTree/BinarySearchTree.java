package uy.edu.um.prog2.adt.tads.MyBinarySearchTree;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

public interface BinarySearchTree  <K extends Comparable<K>, T> {
        T find (K key);
        void insert (K key, T data);
        void delete (K key);
        ListaEnlazada preOrder();
        ListaEnlazada inOrder();
        ListaEnlazada posOrder();
    }

