package uy.edu.um.prog2.adt;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import uy.edu.um.prog2.adt.exceptions.*;

public  class Main {
    static void menu() {
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
    static void  mostTenActivePilotsInTweets() {
        System.out.println("Ingrese mes");
        Scanner scanMonth = new Scanner(System.in);
        int optionMonth = scanMonth.nextInt();
        scanMonth.close();
        Scanner scanYear = new Scanner(System.in);
        int optionYear = scanMonth.nextInt();
        scanYear.close();
        //Hacer verificaciones
    }
    static void topFifteenUsers() {
    }
    public static void dateOk(Scanner scanner) {
        System.out.print("Ingresa una fecha en formato YYYY-MM-DD: ");
        String pendingDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(pendingDate);
        LocalDate startDate = LocalDate.parse("2021-07-01");
        LocalDate endDate = LocalDate.parse("2022-08-31");
        if (date.isBefore(startDate) || date.isAfter(endDate)) {
            dateOk(scanner);
        }
    }
    static void numberOfDifferentHashTagOnADay() {
        Scanner scanDate = new Scanner(System.in);
        dateOk(scanDate);
        scanDate.close();
    }
    static void mostUsedHashTag() {
        Scanner scanTotalDate = new Scanner(System.in);
        dateOk(scanTotalDate);
        scanTotalDate.close();
    }
    static void topSevenUsersWithFav() {

    }
    static void numberOfTweetsWithASpecificWord() {

    }
    public static void getDriversFromFile() {
        ListaEnlazada<String> driversLinkedList = new ListaEnlazada<>();
        final String driversFile = "src/main/resources/drivers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // driversLinkedList.imprimirLista();
    }
    /*public static void getCsvInfo() throws FileNotValidException {
        final String csvFile = "src/main/resources/f1_dataset_test.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                User user = new User();
                Tweet tweet = new Tweet();
                HashTag hashTag = new HashTag();
                try {

            }
        } catch (IOException e) {
            throw new FileNotValidException("FILE_ERROR_FORMAT", e);
        }
    }*/
    static ListaEnlazada<User> userList = new ListaEnlazada<>();
    ListaEnlazada<Tweet> tweetList = new ListaEnlazada<>();
    ListaEnlazada<HashTag> hashtagList = new ListaEnlazada<>();
    public static void getCsvInfo() throws FileNotValidException {
        final String csvFile = "src/main/resources/f1_dataset_test.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            int count = 0;
            for (CSVRecord csvRecord : csvParser) {
                String[] values = csvRecord.values();
                try {
                    User user = new User();
                    Tweet tweet = new Tweet();
                    HashTag hashTag = new HashTag();
                    user.setName(values[1]);
                    user.setUserFavourites(Integer.parseInt(values[7]));
                    user.setVerified(Boolean.parseBoolean(values[8]));
                    user.setIdUser(Integer.parseInt(values[0]));
                    tweet.setRetweet(Boolean.parseBoolean(values[13]));
                    tweet.setSourceTweet(values[12]);
                    tweet.setContentTweet(values[10]);
                    user.getlistaTweet().add(tweet);
                    userList.add(user);
                    //tweet.setHashTagTweet(values[11]);
                    count = ++count;
                } catch (Exception ignored) {
                    }
            }
        } catch (IOException e) {
            throw new FileNotValidException("FILE_ERROR_FORMAT", e);
        }
    }

    public static void main(String[] args) throws FileNotValidException {
        //getDriversFromFile();
        getCsvInfo();
        System.out.println(userList);
        //menu();

    }
}