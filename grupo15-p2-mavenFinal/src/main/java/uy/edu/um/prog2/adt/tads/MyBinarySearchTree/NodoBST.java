package uy.edu.um.prog2.adt.tads.MyBinarySearchTree;

public class NodoBST<K extends Comparable<K>, T> implements Comparable<NodoBST<K, T>> {
    private K key;
    private T value;
    private NodoBST<K, T> left;
    private NodoBST<K, T> right;

    public NodoBST(K key, T value) {
        this.key = key;
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodoBST<K, T> getLeft() {
        return left;
    }

    public void setLeft(NodoBST<K, T> left) {
        this.left = left;
    }

    public NodoBST<K, T> getRight() {
        return right;
    }

    public void setRight(NodoBST<K, T> right) {
        this.right = right;
    }

    public NodoBST<K, T> findMin() {
        NodoBST<K, T> encontrar = this;
        if (getLeft() != null) {
            encontrar = getLeft().findMin();
        }
        return encontrar;
    }


    // Agregar nuevo nodo al BST de manera recursiva.
    public void ingresoRec(K key, T value) {
        NodoBST<K, T> elementoNuevo = new NodoBST<>(key, value);
        if (key.compareTo(getKey()) > 0) {
            if (getRight() == null) {
                setRight(elementoNuevo);
            } else {
                getRight().ingresoRec(key, value); // llama al mismo método
            }
        } else {
            if (getLeft() == null) {
                setLeft(elementoNuevo);
            } else {
                getLeft().ingresoRec(key, value); // llama al mismo método
            }
        }
    }

    // Sacar elemento

    public NodoBST<K, T> deleteRec(K key) {
        int valores = key.compareTo(key);
        if (valores < 0) {
            if (getLeft() != null) {
                setLeft(getLeft().deleteRec(key));
            }
        } else if (valores > 0) {
            if (getRight() != null) {
                setRight(getRight().deleteRec(key));
            }
        } else if (getRight() != null && getLeft() != null) {
            NodoBST<K, T> aux = getRight().findMin();
            setKey(aux.getKey());
            setValue(aux.getValue());
            setRight(getRight().deleteRec(aux.getKey()));
        } else {
            if (getLeft() != null) {
                return getLeft();
            } else {
                return getRight();
            }
        }
        return this;
    }

    public int compareTo(NodoBST<K, T> nuevoNodo) {
        return 0;
    }
}
