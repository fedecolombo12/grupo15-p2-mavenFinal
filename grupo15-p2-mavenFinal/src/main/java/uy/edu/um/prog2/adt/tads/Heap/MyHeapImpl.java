package uy.edu.um.prog2.adt.tads.Heap;
import java.util.Arrays;

public class MyHeapImpl<K extends Comparable<K>, T> implements MyHeap<K, T> {
    private NodoHeap<K, T>[] nodoHeaps;
    private int size = 0;

    public NodoHeap<K, T>[] getNodoHeaps() {
        return nodoHeaps;
    }

    public MyHeapImpl(int len) {
        nodoHeaps = new NodoHeap [len];
    }

    @Override
    public void insert(K key, T data) {
        nodoHeaps[size] = new NodoHeap<>(key, data);
        size++;
        if (size == nodoHeaps.length) {
            aumentarCap();
        }
        heapifyUp();
    }

    @Override
    public T extractMax() {
        if (size == 0) {
            return null;
        }
        T max = nodoHeaps[0].getValue();
        nodoHeaps[0] = nodoHeaps[size-1];
        nodoHeaps[size-1] = null;
        size--;
        heapifyDown();
        //heapifyUp();
        return max;
    }

    private void intercambiar(int menorInt, int mayorInt) {
        // Se crean variables temporales donde guardar nodos
        NodoHeap<K, T> mayorNode = nodoHeaps[mayorInt];
        NodoHeap<K, T> menorNode = nodoHeaps[menorInt];

        nodoHeaps[menorInt] = mayorNode; // El mayor nodo se coloca en la p
        nodoHeaps[mayorInt] = menorNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean is_empty() {
        return size == 0;
    }

    @Override
    public void heapifyUp() {
        for (int j = size-1; j>0; j--){
            int father = (j-1)/2;
            // Si padre es menor que 0, llegamos a la raíz del Heap, no hay
            // más elementos para comparar.
            if (father < 0){
                return;
                // con el return si 'father' es menor a 0, se detiene el método.
            }
            if (nodoHeaps[j].getKey().compareTo(nodoHeaps[father].getKey()) > 0){
                intercambiar(j, father);
            }
        }
    }

    @Override
    public void heapifyDown() {
        for (int j = 0; j<size; j++){
            int leftCh = (2 * j) + 1;
            int rightCh = (2 * j) + 2;
            Integer newFather = null;
            // Chequear si existe leftCh y si su key es mayor que la del padre
            if (leftCh < size && nodoHeaps[j].getKey().compareTo(nodoHeaps[leftCh].getKey()) < 0){
                newFather = leftCh;
                // Chequeamos si existe rightCh y si su key es mayor que la del padre y leftCh
                if (rightCh < size && nodoHeaps[j].getKey().compareTo(nodoHeaps[rightCh].getKey()) < 0 && nodoHeaps[newFather].getKey().compareTo(nodoHeaps[rightCh].getKey()) < 0){
                    newFather = rightCh; }
                // Chequear si existe rightCh y si su key es mayor que la del padre
            } else if (rightCh < size && nodoHeaps[j].getKey().compareTo(nodoHeaps[rightCh].getKey()) < 0) {
                newFather = rightCh;
            }
            if (newFather != null){
                intercambiar(j, newFather);
            }
        }
    }


    // Ante el problema de estar utilizado un Array de un tamaño determinado
    // se crea aumentarCap
    private void aumentarCap() {
        int nuevaCap = (int) (nodoHeaps.length * 2);
        if (nuevaCap == nodoHeaps.length){
            // copyOf: copia el Array determinado
            nodoHeaps = Arrays.copyOf(nodoHeaps, nuevaCap+1);
            return;
        }
        // Obtengo un Array de un mayor tamaño para poder guardar más datos.
        nodoHeaps = Arrays.copyOf(nodoHeaps, nuevaCap);
    }


    public <K extends Comparable<K>, T> void imprimirArreglo(NodoHeap<K, T>[] arreglo) {
        for (NodoHeap<K, T> nodo : arreglo) {
            if (nodo != null){
                System.out.println(nodo.getKey() + ": " + nodo.getValue());
            }
        }

    }
}
