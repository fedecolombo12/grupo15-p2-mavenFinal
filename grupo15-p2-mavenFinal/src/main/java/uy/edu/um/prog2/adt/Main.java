package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;
import uy.edu.um.prog2.adt.tads.Hash.*;
import uy.edu.um.prog2.adt.tads.Heap.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import uy.edu.um.prog2.adt.ReadCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.nio.file.Paths;
import java.nio.file.Files;


import uy.edu.um.prog2.adt.exceptions.*;

public  class Main {
    static ReadCSV readCSVImpl = new ReadCSV();
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
            inputWord();
        } else if (option == 0) {
            System.out.println("El programa finalizo. Muchas gracias.");
        } else {
            System.out.println("Elige un numero del 0 al 6");
            menu();
        }
        scanner.close();
    }

    private static void mostTenActivePilotsInTweets() {

    }

    static void function_1() throws WrongDate {
        System.out.println("Ingrese mes en formato MM");
        Scanner scanMonth = new Scanner(System.in);
        int optionMonth = scanMonth.nextInt();
        scanMonth.close();
        System.out.println("Ingrese año en formato YYYY");
        Scanner scanYear = new Scanner(System.in);
        int optionYear = scanYear.nextInt();
        scanYear.close();
        verify(optionMonth,optionYear);
    }
    static void topFifteenUsers() {
    }
    public static void verify(int month, int year) throws WrongDate {
        if (year == 2021) {
            if (month >= 07 && month <= 12) {
                mostTenActivePilotsInTweets();
            }
        } else if (year == 2022) {
            if (month >= 01 && month <= 8) { // se arregla dsp
                mostTenActivePilotsInTweets();
            }
        } else {
            throw new WrongDate("Ingresar una fecha dentro de ese rango");
        }
    }
    static void numberOfDifferentHashTagOnADay() {

    }
    static void mostUsedHashTag() {

    }
    static void topSevenUsersWithFav() {

    }
    public static void inputWord()  {
        System.out.println("Ingrese la palabra");
        Scanner scanWord = new Scanner(System.in);
        String optionWord = scanWord.nextLine();
        scanWord.close();
        numberOfTweetsWithASpecificWord(optionWord);
    }
    static void numberOfTweetsWithASpecificWord(String word) {
        int counterTweets = 0;
        for (int i = 0; i < readCSVImpl.getTweetList().size() ; i++) {
            if (readCSVImpl.getTweetList().get(i).getContentTweet().contains(word)) {
                counterTweets++;
            }
        }
        System.out.println("La cantidad de Tweets con la palabra" + word + "son" + counterTweets);
    }
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
        driversLinkedList.imprimirLista();

    }
    public static void main(String[] args) throws FileNotValidException, IOException, WrongDate {
        ListaEnlazada<String> driversLinkedList = new ListaEnlazada<>();
        getDriversFromFile(driversLinkedList);
        menu();


    }
}