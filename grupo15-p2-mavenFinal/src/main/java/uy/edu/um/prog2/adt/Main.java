package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import uy.edu.um.prog2.adt.exceptions.*;
import uy.edu.um.prog2.adt.tads.Lista.NodoLista;

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
            function_1();
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
    static void function_1() throws WrongDate {
        System.out.println("Ingrese año en formato YYYY");
        Scanner scanYear = new Scanner(System.in);
        int optionYear = scanYear.nextInt();
        if (optionYear == 2021 || optionYear == 2022){
            System.out.println("Ingrese mes en formato MM");
            Scanner scanMonth = new Scanner(System.in);
            int optionMonth = scanMonth.nextInt();
            scanMonth.close();
            scanYear.close();
            verify(optionMonth,optionYear);
        } else {
            throw new WrongDate("Ingrese un año dentro del rango");
        }
    }
    public static void verify(int month, int year) throws WrongDate {
        if (year == 2021) {
            if (month >= 07 && month <= 12) {
                mostTenActivePilotsInTweets();
            }
        } else if (year == 2022) {
            if (month >= 01 && month <= 8) { // se arregla dsp
                mostTenActivePilotsInTweets();
            } else {
                throw new WrongDate("Ingresar una fecha dentro del rango 2021-07 y 2022-08");
            }
        } else {
            throw new WrongDate("Ingresar una fecha dentro del rango 2021-07 y 2022-08");
        }
    }
    private static void mostTenActivePilotsInTweets() {

    }

    // ----------------------------------------------- FUNCTION 2 ------------------------------------------------------
    static void topFifteenUsers() {
    }

    // ----------------------------------------------- FUNCTION 3 ------------------------------------------------------
    static void numberOfDifferentHashTagOnADay() {

    }

    // ----------------------------------------------- FUNCTION 4 ------------------------------------------------------

    static void mostUsedHashTag() {

    }

    // ----------------------------------------------- FUNCTION 5 ------------------------------------------------------

    static ListaEnlazada<String> topSevenUsersWithFav() {
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
        return topSeven;
    }

    // ----------------------------------------------- FUNCTION 6 ------------------------------------------------------
    static void numberOfTweetsWithASpecificWord() {
        System.out.println("Ingrese la palabra");
        Scanner scanWord = new Scanner(System.in);
        String optionWord = scanWord.nextLine();
        scanWord.close();
       int counterTweets = 0;
       for (int i = 0; i < readCSVImpl.getTweetList().size() ; i++) {
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
        //readCSVImpl.getCsvInfo(); Probalo asi y anda, si le sacas las //, no larga el menu y tampoco ejecuta el getcsvinfo
        menu();
    }
}