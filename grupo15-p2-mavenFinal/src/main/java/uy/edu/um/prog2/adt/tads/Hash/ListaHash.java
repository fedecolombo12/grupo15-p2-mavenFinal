package uy.edu.um.prog2.adt.tads.Hash;
import uy.edu.um.prog2.adt.tads.Lista.Lista;
import uy.edu.um.prog2.adt.tads.Lista.NodoLista;

public class ListaHash<K, V> implements Lista<NodoHash<K, V>> {
    private NodoHash<K, V> first;
    private NodoHash<K, V> last;

    public ListaHash() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(NodoHash<K, V> node) {
        if (node != null) {
            if (first == null) {
                first = node;
                last = node;
            } else {
                last.setNext(node);
                last = node;
            }
        }
    }

    @Override
    public NodoHash<K, V> get(int position) {
        NodoHash<K, V> node = null;
        if (position == 0) {
            return first;
        }
        NodoHash<K, V> current = first.getNext();
        int size = this.size();
        int posiciones = size - 1;
        for (int i = 1; i <= posiciones; i++) {
            if (i == position) {
                return current;
            }
            current = current.getNext();
        }
        return node;
    }

    @Override
    public boolean contains(NodoHash<K, V> value) { return false;
    }

    public V get(K key) {
        NodoHash<K, V> current = first;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null; // Si no se encuentra la clave, retorna null o puedes lanzar una excepción
    }
    public NodoHash<K, V> getNode(K key) {
        NodoHash<K, V> current = first;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current;
            }
            current = current.getNext();
        }
        return null; // Si no se encuentra la clave, retorna null o puedes lanzar una excepción
    }

    @Override
    public void remove(NodoHash<K, V> node) {
        NodoHash<K, V> beforeCurrentNode = null;
        NodoHash<K, V> currentNode = this.first;
        while (currentNode != null && currentNode != node) {
            beforeCurrentNode = currentNode;
            currentNode = beforeCurrentNode.getNext();
        }
        if (currentNode != null) {
            if (currentNode == node) {
                if (currentNode == this.first && currentNode == this.last) {
                    this.first = null;
                } else if (currentNode == this.first) {
                    this.first = currentNode.getNext();
                } else if (currentNode == this.last) {
                    this.last = beforeCurrentNode;
                    this.last.setNext(null);
                } else {
                    beforeCurrentNode.setNext(currentNode.getNext());
                }
            }
        }
    }

    @Override
    public int size() {
        if (first == null) {
            return 0;
        } else {
            int size = 1;
            NodoHash<K, V> current = first;
            while (current.getNext() != null) {
                size++;
                current = current.getNext();
            }
            return size;
        }
    }

    @Override
    public void addFirst(NodoHash<K, V> value) {

    }

    @Override
    public void change(NodoHash<K, V> value, int direction) {

    }

    @Override
    public void addOrder(NodoHash<K, V> value) {

    }

    @Override
    public void imprimirLista() {
        return;
    }

    @Override
    public NodoLista<NodoHash<K, V>> searchT(NodoHash<K, V> value) {
        return null;
    }

    public void print() {
        NodoHash<K, V> current = first;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(current.getData() );
            if (current.getNext() != null) {
                current = current.getNext();
            }
        }
    }

    public NodoHash<K, V> getFirst() {
        return first;
    }

    public void setFirst(NodoHash<K, V> first) {
        this.first = first;
    }

    public NodoHash<K, V> getLast() {
        return last;
    }

    public void setLast(NodoHash<K, V> last) {
        this.last = last;
    }


}
