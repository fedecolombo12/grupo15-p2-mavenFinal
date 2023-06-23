package uy.edu.um.prog2.adt.tads.MyBinarySearchTree;

import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

public class BinarySearchTreeImpl<K extends Comparable<K>, T> implements BinarySearchTree<K, T> {
    private NodoBST<K, T> raiz;
    private ListaEnlazada<NodoBST<K, T>> orden;

    public BinarySearchTreeImpl() {
        this.raiz = null;
    }

    public NodoBST<K, T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoBST<K, T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public T find(K key) {
        return findRec(key, getRaiz());
    }

    public T findRec(K keyBuscar, NodoBST<K, T> nuevoNodo) {
        T valInicial = null;
        if (getRaiz() != null) {
            int valor = keyBuscar.compareTo(nuevoNodo.getKey());
            if (valor > 0) {
                valInicial = findRec(keyBuscar, nuevoNodo.getRight());
            } else if (valor == 0) {
                valInicial = getRaiz().getValue();
            } else {
                valInicial = findRec(keyBuscar, getRaiz().getLeft());
            }
        }
        return valInicial;
    }

    @Override
    public void insert(K key, T data) {
        NodoBST<K, T> ingreso = new NodoBST<>(key, data);
        if (getRaiz() == null) {
            setRaiz(ingreso);
        } else {
            getRaiz().ingresoRec(key, data);
        }
    }

    @Override
    public void delete(K key) {
        if (getRaiz() != null) {
            setRaiz(getRaiz().deleteRec(key));
        }
    }

    @Override
    public ListaEnlazada preOrder() {
        ListaEnlazada<NodoBST<K, T>> listPreO = new ListaEnlazada<>();
        this.preOrderR(this.raiz, listPreO);
        return listPreO;
    }

    private void preOrderR(NodoBST<K, T> node, ListaEnlazada<NodoBST<K, T>> nodeListPreO) {
        if (node != null) {
            nodeListPreO.add(node);
            this.preOrderR(node.getLeft(), nodeListPreO);
            this.preOrderR(node.getRight(), nodeListPreO);
        }
    }

    @Override
    public ListaEnlazada inOrder() {
        ListaEnlazada<NodoBST<K, T>> listInO = new ListaEnlazada<>();
        this.inOrderR(this.raiz, listInO);
        return listInO;
    }


    private void inOrderR(NodoBST<K, T> node, ListaEnlazada<NodoBST<K, T>> nodeListInO) {
        if (node != null) {
            this.inOrderR(node.getLeft(), nodeListInO);
            nodeListInO.add(node);
            this.inOrderR(node.getRight(), nodeListInO);
        }
    }


    @Override
    public ListaEnlazada posOrder() {
        ListaEnlazada<NodoBST<K, T>> listPosO = new ListaEnlazada<>();
        this.posOrderR(this.raiz, listPosO);
        return listPosO;
    }

    private void posOrderR(NodoBST<K, T> node, ListaEnlazada<NodoBST<K, T>> nodeListPosO) {
        if (node != null) {
            this.posOrderR(node.getLeft(), nodeListPosO);
            this.posOrderR(node.getRight(), nodeListPosO);
            nodeListPosO.add(node);
        }

    }

    public NodoBST min(NodoBST<K, T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return min(node.getLeft());
    }

    public NodoBST delMin(NodoBST<K, T> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(delMin(node.getLeft()));
        return node;
    }

    public void printTree() {
        printTreeRec(getRaiz());
    }

    private void printTreeRec(NodoBST<K, T> node) {
        if (node != null) {
            printTreeRec(node.getLeft());
            System.out.println(node.getKey() + ": " + node.getValue());
            printTreeRec(node.getRight());
        }
    }


}



