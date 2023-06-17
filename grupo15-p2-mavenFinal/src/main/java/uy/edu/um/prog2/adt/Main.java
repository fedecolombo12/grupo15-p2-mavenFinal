package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.tads.Hash.MyHash;
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
        System.out.println("Menu principal");
        System.out.println("Seleccione la opción del menú: ");
        System.out.println("    1. Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets en un mes");
        System.out.println("    2. Top 15 usuarios con más tweets");
        System.out.println("    3. Cantidad de hashtags distintos para un día dado");
        System.out.println("    4. Hashtag más usado para un día dado");
        System.out.println("    5. Top 7 cuentas con más favoritos");
        System.out.println("    6. Cantidad de tweets con una palabra o frase específicos");
        int option = scanner.nextInt();
        if (option == 1) {
            mostTenActivePilotsInTweets();
        } else if (option == 2) {
            topFifteenUsers();
        } else if(option == 3) {
            numberOfDifferentHashTagOnADay();
        } else if (option == 4) {
            mostUsedHashTag();
        } else if (option == 5) {
            topSevenUsersWithFav();
        } else if (option == 6) {
            numberOfTweetsWithASpecificWord();
        } else if (option == 0) {
            System.out.println("El programa finalizo. Muchas gracias.");
        } else {
            System.out.println("Elige un numero del 0 al 6");
            menu();
        }
        scanner.close();
    }

    // ----------------------------------------------- FUNCTION 1 ------------------------------------------------------

    /* Lo primero que se hace es solicitar a la persona que ejecuta el programa que ingrese al año y mes en el cual
    se quiere que se ejecute la funcion. Se sobreentiende por la letra del problema que no se va a colocar otra fecha
    para que se "rompa" el programa.
    */
    private static void mostTenActivePilotsInTweets() {
        System.out.println("Ingrese año en formato YYYY");
        Scanner scanYear = new Scanner(System.in);
        int optionYear = scanYear.nextInt();
        System.out.println("Ingrese mes en formato MM");
        Scanner scanMonth = new Scanner(System.in);
        int optionMonth = scanMonth.nextInt();
        scanMonth.close();
        scanYear.close();
    }

    // ----------------------------------------------- FUNCTION 2 ------------------------------------------------------
    static void topFifteenUsers() {
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
    static void numberOfDifferentHashTagOnADay() {
        System.out.println("Ingrese año en formato YYYY");
        Scanner scanYear = new Scanner(System.in);
        int optionYear = scanYear.nextInt();
        System.out.println("Ingrese mes en formato MM");
        Scanner scanMonth = new Scanner(System.in);
        int optionMonth = scanMonth.nextInt();
        System.out.println("Ingrese dia en formato DD");
        Scanner scanDay = new Scanner(System.in);
        int optionDay = scanDay.nextInt();
        scanDay.close();
        scanMonth.close();
        scanYear.close();
        String totalDate = optionYear + "-" + optionMonth + "-" + optionDay;
        ListaEnlazada<Long> difHashTag = new ListaEnlazada<>();
        int numberOfDifferentHashTag = 0;
        for (int i = 0; i < readCSVImpl.getTweetList().size(); i++) {
            if (readCSVImpl.getTweetList().get(i).getDate().equals(totalDate)) {
                for (int j = 0; j < readCSVImpl.getTweetList().get(i).getHashTagTweet().size() ; j++) {
                    long hashTagInList = readCSVImpl.getTweetList().get(i).getHashTagTweet().get(i).getIdHashTag();
                    if (!difHashTag.contains(hashTagInList)){
                        difHashTag.add(hashTagInList);
                        numberOfDifferentHashTag++;
                    }
                }
            }
        }
        System.out.println("La cantidad de hashtags distintos para el dia " + totalDate + " son " + numberOfDifferentHashTag);
    }

    // ----------------------------------------------- FUNCTION 4 ------------------------------------------------------

    static void mostUsedHashTag() {

    }

    // ----------------------------------------------- FUNCTION 5 ------------------------------------------------------

    static void topSevenUsersWithFav() {
        ListaEnlazada<String> topSeven = new ListaEnlazada<>();
        readCSVImpl.getUserList().quickSort();
        NodoLista<User> nodo = readCSVImpl.getUserList().getPrimero();
        int count = 0;
        while (nodo != null && count < 7) {
            User user = nodo.getValue(); // Obtener el usuario y la cantidad de favoritos
            String cuenta = user.getName() + " - Favoritos: " + user.getUserFavourites();// Crear una cadena con el nombre del usuario y la cantidad de favoritos
            topSeven.add(cuenta);
            nodo = nodo.getSiguiente();
            count++;
        }
        topSeven.imprimirLista();
    }

    // ----------------------------------------------- FUNCTION 6 ------------------------------------------------------
    static void numberOfTweetsWithASpecificWord() {
        System.out.println("Ingrese la palabra");
        Scanner scanWord = new Scanner(System.in);
        String optionWord = scanWord.nextLine();
        scanWord.close();
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