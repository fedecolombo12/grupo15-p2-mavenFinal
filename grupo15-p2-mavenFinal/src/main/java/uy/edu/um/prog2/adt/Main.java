package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.tads.Hash.MyHash;
import uy.edu.um.prog2.adt.tads.Heap.MyHeap;
import uy.edu.um.prog2.adt.tads.Heap.MyHeapImpl;
import uy.edu.um.prog2.adt.tads.Heap.NodoHeap;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import uy.edu.um.prog2.adt.exceptions.*;
import uy.edu.um.prog2.adt.tads.Lista.NodoLista;
import uy.edu.um.prog2.adt.tads.Hash.*;

/*Se crea el metodo desplegable en el cual se va a elegir entre opciones del 0 al 6 para poder realizar las consultas. Ademas,
se separa con --- cuando comienza y termina cada funcion*/
public class Main {
    private static ReadCSV readCSVImpl;
    static void menu() throws WrongDate {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Menu principal");
            System.out.println("Seleccione la opción del menú: ");
            System.out.println("    1. Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets en un mes");
            System.out.println("    2. Top 15 usuarios con más tweets");
            System.out.println("    3. Cantidad de hashtags distintos para un día dado");
            System.out.println("    4. Hashtag más usado para un día dado");
            System.out.println("    5. Top 7 cuentas con más favoritos");
            System.out.println("    6. Cantidad de tweets con una palabra o frase específicos");
            System.out.println("    0. Para salir del programa");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> mostTenActivePilotsInTweets(scanner);
                case 2 -> topFifteenUsers(scanner);
                case 3 -> numberOfDifferentHashTagOnADay(scanner);
                case 4 -> mostUsedHashTag(scanner);
                case 5 -> topSevenUsersWithFav(scanner);
                case 6 -> numberOfTweetsWithASpecificWord(scanner);
                case 0 -> System.out.println("El programa finalizó. Muchas gracias.");
                default -> System.out.println("Elige un número del 0 al 6");
            }
        } while (option != 0);
        scanner.close();
    }

    // ----------------------------------------------- FUNCTION 1 ------------------------------------------------------

    /* Lo primero que se hace es solicitar a la persona que ejecuta el programa que ingrese al año y mes en el cual
    se quiere que se ejecute la funcion. Se sobreentiende por la letra del problema que no se va a colocar otra fecha
    para que se "rompa" el programa.
    */
    private static void mostTenActivePilotsInTweets(Scanner scanner) {
        TablaHash<String,Integer> hash = new TablaHash<>(50);
        System.out.println("Ingrese año en formato YYYY");
        scanner.nextLine();
        int optionYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese mes en formato MM");
        int optionMonth = scanner.nextInt();

        ListaEnlazada<String> driversListaEnlazada = new ListaEnlazada<>();
        getDriversFromFile(driversListaEnlazada);

        NodoLista<Tweet> tweet = readCSVImpl.getTweetList().getPrimero();

        while (tweet != null){
            NodoLista<String> driver = driversListaEnlazada.getPrimero();
            String dateTweet = tweet.getValue().getDate();
            String[] dateTweetA = dateTweet.split("-");
            int year = Integer.parseInt(dateTweetA[0]);
            int month = Integer.parseInt(dateTweetA[1]);
            if (optionYear == year && optionMonth == month ){
                while (driver != null){
                String contentTweet = tweet.getValue().getContentTweet().toLowerCase();
                String driverName = driver.getValue().toLowerCase();

                if (!hash.contains(driverName)){
                    hash.put(driverName,0);
                }
                if ((contentTweet.toLowerCase().contains(driverName))){
                    int count = hash.get(driverName);
                    hash.put(driverName, count + 1);
                }
                driver = driver.getSiguiente();
            }
        }
            tweet = tweet.getSiguiente();
        }
        ListaEnlazada<NodoHash<String, Integer>> nodoList = new ListaEnlazada<>();
        for (int i = 0; i < hash.size(); i++) {
            try {
                ListaHash<String, Integer>[] buckets = hash.getBuckets(i);
                if (buckets.length > 0) {
                    NodoHash<String, Integer> nodoActual = buckets[0].getFirst();
                    while (nodoActual != null) {
                        nodoList.add(nodoActual);
                        nodoActual = nodoActual.getNext();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MyHeapImpl<Integer,NodoHash<String, Integer>> driversHeap = new MyHeapImpl(nodoList.size());
        NodoLista<NodoHash<String,Integer>> nodo = nodoList.getPrimero();
        while (nodo != null){
            driversHeap.insert(nodo.getValue().getData(), nodo.getValue());
            nodo = nodo.getSiguiente();
        }
        NodoHash<String, Integer>[] topTen = new NodoHash[10];
        for (int i = 0; i < 10; i++) {
            NodoHash<String, Integer> nuevoNodo = driversHeap.extractMax();
            if (nuevoNodo != null){
                topTen[i] = nuevoNodo;
                System.out.println(nuevoNodo.getKey() + " con " + nuevoNodo.getData() + " ocurrencias.");
            }
        }
    }

    // ----------------------------------------------- FUNCTION 2 ------------------------------------------------------
    static void topFifteenUsers(Scanner scanner) {
        MyHeapImpl<Integer, User> ranking = new MyHeapImpl<>(readCSVImpl.getUserList().size());
        for (int i = 1; i < readCSVImpl.getUserList().size(); i++) {
            ranking.insert(readCSVImpl.getUserList().get(i).getlistaTweet().size(), readCSVImpl.getUserList().get(i));
        }
        for (int c = 1; c <= 15; c++) {
            User user = ranking.extractMax();
            System.out.println("Nº " + c + " Cantidad de tweets: " + user.getlistaTweet().size() +
                    "  Verificado: " + user.isVerified() + " Usuario: " + user.getName());
        }
    }

    /* REVISAR

    public static void getTopFifteen(List<Tweet> listOfTweets){
        // El tamaño de la tabla se puede cambiar
        MyHash<String, User> usersHash = new TablaHash<>(50);

        for (Tweet tweets : listOfTweets){
            User userName = tweets.getUser();
            String user = userName.getName();

            // Chequeo si el usuario ya está en el Hash o no
            if (!usersHash.contains(user)){
                // lo agrego a la tabla
                usersHash.put(user, userName);
            }

            User newUser = usersHash.get(user);
            newUser.getCountTweets();
            }

        List<User> topUser = getTopUsers(usersHash,15);

        for (User user: topUser){
            System.out.println("Usuario: " + user + ", cantidad de tweets: " + user.getCountTweets());
        }

     */



        /*
        REVISAR
        public static List<User> getTopUsers(MyHash<String,User> userMyHash, int n){
        List<User> users = new ArrayList<>(userMyHash.size());

            for (int i = 0; i < users.size() ; i++) {
                ListaHash<String,User> bucket =

            NodoHash<String, User> aux = bucket.getFirst();
            while (aux != null){
                users.add(aux.getData());
                aux = aux.getNext();
            }
        }
        Collections.sort(users, Comparator.comparingInt(User::getCountTweets).reversed());
        return users.subList(0, Math.min(n, users.size()));
        }
    */


    // ----------------------------------------------- FUNCTION 3 ------------------------------------------------------

    /* Se solicita a la persona que ejecuta el programa, el año (2021 o 2022), mes y dia en el cual queremos analizar la
    cantidad de Hashtags diferentes en un dia.*/
    static void numberOfDifferentHashTagOnADay(Scanner scanner) {
        System.out.println("Ingrese año en formato YYYY");
        scanner.nextLine();
        int optionYear = scanner.nextInt();
        System.out.println("Ingrese mes en formato MM");
        int optionMonth = scanner.nextInt();
        System.out.println("Ingrese dia en formato DD");
        int optionDay = scanner.nextInt();
        String totalDate = optionYear + "-" + optionMonth + "-" + optionDay;
        ListaEnlazada<String> difHashTag = new ListaEnlazada<>();
        int numberOfDifferentHashTag = 0;
        for (int i = 1; i <= readCSVImpl.getTweetList().size(); i++) {
            if (readCSVImpl.getTweetList().get(i).getDate().equals(totalDate)) {
                for (int j = 1; j <= readCSVImpl.getTweetList().get(i).getHashTagTweet().size() ; j++) {
                    String hashTagInList = readCSVImpl.getTweetList().get(i).getHashTagTweet().get(j).getTextHashTag().toLowerCase();
                    if (!(difHashTag.contains((hashTagInList)))){
                        difHashTag.add(hashTagInList);
                        numberOfDifferentHashTag++;
                    }
                }
            }
        }
        System.out.println("La cantidad de hashtags distintos para el dia " + totalDate + " son " + numberOfDifferentHashTag);
    }

    // ----------------------------------------------- FUNCTION 4 ------------------------------------------------------
    static void mostUsedHashTag(Scanner scanner) {
        TablaHash<String,Integer> hashHashtag = new TablaHash<>(50);
        System.out.println("Ingrese año en formato YYYY");
        scanner.nextLine();
        int optionYear = scanner.nextInt();
        System.out.println("Ingrese mes en formato MM");
        scanner.nextLine();
        int optionMonth = scanner.nextInt();
        System.out.println("Ingrese dia en formato DD");
        scanner.nextLine();
        int optionDay = scanner.nextInt();
        String totalDate = optionYear + "-" + optionMonth + "-" + optionDay;
        String maxHashtag = null;
        int maxCount = 0;
        NodoLista<Tweet> aux = readCSVImpl.getTweetList().getPrimero();
        while (aux != null) {
            if(aux.getValue().getDate().equals(totalDate)) {
                NodoLista<HashTag> auxHashtag = aux.getValue().getHashTagTweet().getPrimero();
                while (auxHashtag != null) {
                    String hashtagText = auxHashtag.getValue().getTextHashTag();
                    if (!notF1(hashtagText)) {
                        if (!hashHashtag.contains(hashtagText)) {
                            hashHashtag.put(hashtagText, 1);
                        } else {
                            int newCount = hashHashtag.get(hashtagText) + 1;
                            hashHashtag.put(hashtagText, newCount);
                            if (newCount > maxCount) {
                                maxHashtag = hashtagText;
                                maxCount = newCount;
                            }
                        }
                    }
                    auxHashtag = auxHashtag.getSiguiente();
                }
            }
            aux = aux.getSiguiente();
        }
        if (maxHashtag != null) {
            System.out.println("El hashtag más utilizado es: " + maxHashtag + " y aparece " + maxCount + " veces");
        }
    }
    private static boolean notF1(String word) {
        String not = "f1"; // Agrega aquí todas las palabras prohibidas
        if (word.equalsIgnoreCase(not)) {
            return true;
        }
        return false;
}

    // ----------------------------------------------- FUNCTION 5 ------------------------------------------------------

    static void topSevenUsersWithFav(Scanner scanner) {
        MyHeapImpl<Integer, User> heapUsers = new MyHeapImpl<>(readCSVImpl.getUserList().size());
        for (int i = 1; i < readCSVImpl.getUserList().size(); i++) {
            heapUsers.insert(readCSVImpl.getUserList().get(i).getUserFavourites(),readCSVImpl.getUserList().get(i));
        }
        User[] top7 = new User[7];
        for (int i = 0; i < 7 ; i++){
            top7[i] = heapUsers.extractMax();
        }
        System.out.println("Las 7 cuentas con más favoritos son: ");
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.println("Nombre: " + top7[i].getName());
            System.out.println("Favoritos: " + top7[i].getUserFavourites());
            System.out.println();
        }
    }

    // ----------------------------------------------- FUNCTION 6 ------------------------------------------------------
    static void numberOfTweetsWithASpecificWord(Scanner scanner) throws WrongDate {
        System.out.println("Ingrese la palabra");
        scanner.nextLine();
        String optionWord = scanner.nextLine();
        int counterTweets = 0;
        for (int i = 1; i <= readCSVImpl.getTweetList().size() ; i++) {
            if (readCSVImpl.getTweetList().get(i).getContentTweet().toLowerCase().contains(optionWord.toLowerCase())) {
                counterTweets++;
            }
        }
        System.out.println("La cantidad de Tweets con la palabra " + optionWord + " son " + counterTweets);
    }

    // -------------------------------------------- LECTURA DE DATOS----------------------------------------------------

    public static void getDriversFromFile(ListaEnlazada<String> driversLinkedList) {
        final String driversFile = "grupo15-p2-mavenFinal/src/main/resources/drivers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws FileNotValidException, IOException, WrongDate {
        ListaEnlazada<String> driversLinkedList = new ListaEnlazada<>();
        readCSVImpl = new ReadCSV();
        getDriversFromFile(driversLinkedList);
        readCSVImpl.getCsvInfo();
        menu();
    }
}