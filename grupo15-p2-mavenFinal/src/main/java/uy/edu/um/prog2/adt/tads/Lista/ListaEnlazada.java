package uy.edu.um.prog2.adt.tads.Lista;

public class ListaEnlazada<T extends Comparable<T>> implements Lista<T>{
    private NodoLista<T> primero;
    private NodoLista<T> ultimo;
    public NodoLista<T> getPrimero() {
        return primero;
    }
    public void setPrimero(NodoLista<T> primero) {
        this.primero = primero;
    }
    public NodoLista<T> getUltimo() {
        return ultimo;
    }
    public void setUltimo(NodoLista<T> ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public void add(T value) {
        if (getPrimero() == null){ // si la lista es vacia lo agrega al pricipio
            NodoLista<T> nuevonodo = new NodoLista<>(value);
            setPrimero(nuevonodo);
            setUltimo(nuevonodo);
        } else {
            NodoLista<T> aux = getUltimo(); // addLast
            NodoLista<T> nuevonodo = new NodoLista<>(value);
            aux.setSiguiente(nuevonodo);
            setUltimo(nuevonodo);
        }
    }
    @Override
    public void addFirst(T value) {
        NodoLista<T> nuevonodo = new NodoLista<>(value);
        setPrimero(nuevonodo);
        if (getPrimero() == null){ // si la lista es vacia lo agrega al pricipio
            setUltimo(nuevonodo);
        } else {
            NodoLista<T> aux = nuevonodo.getSiguiente();
            nuevonodo.setSiguiente(aux);
        }
    }
    @Override
    public void addOrder(T value) { // no es eficiente porque recorro cada vez que arreglo toda la lista
        NodoLista<T> nuevonodo = new NodoLista<>(value);
        if (getPrimero() == null){ // si la lista es vacia lo agrega al pricipio
            setPrimero(nuevonodo);
            setUltimo(nuevonodo);
        } else {
            NodoLista<T> aux = getPrimero();
            while (aux != null) {
                if (nuevonodo.getValue().compareTo(aux.getValue()) < 0) {

                }
                aux = aux.getSiguiente();
            }
        }
    }
    @Override
    public void remove(T value) {
        NodoLista<T> before = null;
        NodoLista<T> aux = getPrimero();
        while (aux != null && aux.getValue() != value) { // Busco el elemento a eliminar t
            before = aux;
            aux = aux.getSiguiente();
        }
        if (aux != null) { // si lo encontre
            if (aux == getPrimero() && aux != getUltimo()) {
                NodoLista<T> temp = getPrimero();
                primero = getPrimero().getSiguiente(); // salteo el primero
                temp.setSiguiente(null); // quito referencia del elemento eliminado al siguiente.
                // Verifico si es el ultimo valor (caso borde) y no el primero
            } else if (aux == getUltimo() && aux != getPrimero()) {
                before.setSiguiente(null);
                ultimo = before;
            } else if (aux == getPrimero() && aux == getUltimo()) { // un solo valor
                primero = null;
                ultimo = null;
            } else { // resto de los casos
                before.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(null);
            }
        }
    }
    @Override
    public T get(int position) {
        T result = null;
        NodoLista<T> aux = getPrimero();
        if (position <= size()) {
            if (position == 1) {
                result = aux.getValue();
            } else if (position == size()) {
                result = getUltimo().getValue();
            }
            else {
                int contador = 1;
                while (aux.getSiguiente() != null){
                    aux = aux.getSiguiente();
                    contador++;
                    if (contador == position) {
                        result = aux.getValue();
                    }
                }
            }
        }
        else {
            System.out.println("No existe esa posicion en la lista");
        }
        return result;
    }

    public T getPosition(int position) {
        if (position > size()){
            return null;
        }
        else {
            NodoLista<T> newNode = this.primero;
            while (position > 0){
                newNode = newNode.getSiguiente();
                position = position - 1;
            }
            if (newNode == null){
                return null;
            }
            return newNode.getValue();
        }
    }

    @Override
    public boolean contains(T value) {
        boolean ok = false;
        NodoLista<T> aux = getPrimero();
        while (aux != null && aux.getValue() != value) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            ok = true;
        }
        return ok;
    }
    @Override
    public int size() {
        int size = 0;
        if (getPrimero() == null) {
            return size;
        }
        NodoLista<T> aux = getPrimero();
        size++;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
            size++;
        }
        return size;
    }

    @Override
    public void change(T value, int direction) {  // para lista enlazada
        NodoLista<T> aux = getPrimero();
        NodoLista<T> aux2 = null;
        while (aux != null && aux.getValue() != value) {
            aux2 = aux;
            aux = aux.getSiguiente();
        }
        if (aux != null) { // si lo encontre
            if (aux == getPrimero() && aux != getUltimo()) { // si es el primero
                if (direction == -1) {
                    System.out.println("No se puede modificar el valor porque es el primer valor");
                } else if (direction == 1) {
                    aux2 = aux.getSiguiente();
                    T valor = aux.getValue();
                    aux.setValue(aux2.getValue());
                    aux2.setValue(valor);
                }
            } else if (aux == getUltimo() && aux != getPrimero()) { // si el el ultimo
                if (direction == 1) {
                    System.out.println("No se puede modificar el valor porque es el primer valor");
                } else if (direction == -1) {
                    T valor = aux.getValue();
                    aux.setValue(aux2.getValue());
                    aux2.setValue(valor);
                }
            } else if (aux == getPrimero() && aux == getUltimo()) { // un solo valor
                System.out.println("No se puede modificar el valor porque es el unico");
            } else { // resto de los casos - aux != getUltimo() && aux != getPrimero()
                if (direction == 1) {
                    aux2 = aux.getSiguiente();
                    T valor = aux.getValue();
                    aux.setValue(aux2.getValue());
                    aux2.setValue(valor);
                } else if (direction == -1) {
                    T valor = aux.getValue();
                    aux.setValue(aux2.getValue());
                    aux2.setValue(valor);
                }
            }
        }
    }
    private boolean content(T data) {
        NodoLista<T> aux = getPrimero();
        while (aux != null) {
            if (aux.getValue() == data) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public void imprimirLista() {
        NodoLista<T> aux = getPrimero();
        while (aux != null) {
            System.out.print(aux.getValue() + ", ");
            aux = aux.getSiguiente();
        }
        System.out.println();
    }
    @Override
    public void quickSort() {
        primero = quickSortRecursivo(getPrimero(), getUltimo());
    }
    public NodoLista<T> quickSortRecursivo(NodoLista<T> inicio, NodoLista<T> fin) {
        if (inicio == null || inicio == fin) {
            return inicio;
        }
        NodoLista<T> pivote = partition(inicio, fin);
        NodoLista<T> prev = null;
        NodoLista<T> aux = inicio;
        while (aux != pivote) {
            prev = aux;
            aux = aux.getSiguiente();
        }
        if (prev != null) {
            prev.setSiguiente(null);
            inicio = quickSortRecursivo(inicio, prev);
            prev.setSiguiente(pivote);
        } else {
            inicio = quickSortRecursivo(inicio, pivote);
            primero = pivote;
        }
        pivote.setSiguiente(quickSortRecursivo(pivote.getSiguiente(), fin));
        return inicio;
    }
    public NodoLista<T> partition(NodoLista<T> inicio, NodoLista<T> fin) {
        T pivotValue = fin.getValue();
        NodoLista<T> i = inicio;
        NodoLista<T> j = inicio;
        while (j != fin) {
            if (j.getValue().compareTo(pivotValue) < 0) {
                T temp = i.getValue();
                i.setValue(j.getValue());
                j.setValue(temp);
                i = i.getSiguiente();
            }
            j = j.getSiguiente();
        }
        T temp = i.getValue();
        i.setValue(fin.getValue());
        fin.setValue(temp);
        return i;
    }
}
