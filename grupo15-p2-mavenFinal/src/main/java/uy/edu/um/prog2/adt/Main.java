package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;
import uy.edu.um.prog2.adt.tads.Hash.*;
import uy.edu.um.prog2.adt.tads.Heap.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

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
    public static void getDriversFromFile(ListaEnlazada<String> driversLinkedList) {
        final String driversFile = "/Users/fede/Desktop/Obligatorio/grupo15-p2-mavenFinal/grupo15-p2-mavenFinal/src/main/resources/drivers.txt";
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
    public static void getCsvInfo() throws FileNotValidException, IOException {
        ListaEnlazada<User> userList = new ListaEnlazada<>();
        ListaEnlazada<HashTag> hashtagList = new ListaEnlazada<>();
        final String csvFile = "grupo15-p2-mavenFinal/src/main/resources/f1_dataset_test.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            csvParser.iterator().next();  // Salta la primera fila que contiene los nombres de las columnas
            long userIdCounter = 1;
            long hashtagIdCounter = 1;
            for (CSVRecord csvRecord : csvParser) {
                String[] values = csvRecord.values();
                String username = values[1];
                User user = buscarUsuario(username, userList);
                if (user == null) {// El usuario no existe en la lista, crea uno nuevo y establece sus atributos
                    user = new User();
                    user.setIdUser(userIdCounter++);
                    user.setName(username);
                    user.setVerified(Boolean.parseBoolean(values[8]));
                    user.setUserFavourites((int) Double.parseDouble(values[7]));
                    userList.add(user); // Agrega el usuario a la lista
                }
                Tweet tweet = new Tweet();
                long tweetId = Long.parseLong(values[0]);
                tweet.setIdTweet(tweetId);
                tweet.setContentTweet(values[10]);
                tweet.setSourceTweet(values[12]);
                tweet.setRetweet(Boolean.parseBoolean(values[13]));
                tweet.setDate(values[9]);
                String hashtagsColumn = values[11];
                if (!hashtagsColumn.isEmpty()) {
                    String[] hashtags = hashtagsColumn.split(",");
                    for (String hashtagText : hashtags) {
                        String trimmedHashtagText = hashtagText.trim();
                        // Verifica si el hashtag ya existe en la lista
                        HashTag existingHashtag = buscarHashtagPorTexto(trimmedHashtagText, hashtagList);
                        if (existingHashtag != null) {
                            //tweet.getHashTagTweet().add(existingHashtag);
                        } else {
                            // El hashtag no existe en la lista, crea uno nuevo y asigna un ID único
                            HashTag hashtag = new HashTag();
                            hashtag.setIdHashTag(hashtagIdCounter++);
                            hashtag.setTextHashTag(trimmedHashtagText);
                            // Agrega el nuevo hashtag a la lista y al tweet
                            hashtagList.add(hashtag);
                            //tweet.getHashTagTweet().add(hashtag);
                        }
                    }
                }
                user.getlistaTweet().add(tweet);
            }
                    /*Tweet tweet = new Tweet();
                    HashTag hashTag = new HashTag();
                    User userToAnalise = buscarUsuario(values[1],userList);
                    if (!userList.contains(userToAnalise)) { // Si el usuario no esta en la lista.
                        User user = new User();
                        user.setName(values[1]);
                        userList.add(user);
                        user.setIdUser(Integer.parseInt(values[0]));
                        user.setVerified(Boolean.parseBoolean(values[8]));
                        user.setUserFavourites(Integer.parseInt(values[7]));
                        //tweet.setIdTweet(nose);
                        tweet.setContentTweet(values[10]);
                        tweet.setSourceTweet(values[12]);
                        tweet.setRetweet(Boolean.parseBoolean(values[13]));
                        ///tweet.setDate(formattedDate);
                        hashTag.setTextHashTag(Arrays.toString((values[11].split(","))));
                        tweet.getHashTagTweet().add(hashTag);
                        user.getlistaTweet().add(tweet);
                        userList.add(user);
                    } else { // Si ya esta en la lista, agrego el tweet a su lista de tweets y hashtag a lista de hashtag
                        //tweet.setIdTweet(nose);
                        tweet.setContentTweet(values[10]);
                        tweet.setSourceTweet(values[12]);
                        tweet.setRetweet(Boolean.parseBoolean(values[13]));
                        //tweet.setDate(formattedDate);
                        hashTag.setTextHashTag(Arrays.toString(values[11].split(",")));
                        tweet.getHashTagTweet().add(hashTag);
                        userToAnalise.getlistaTweet().add(tweet);
                    }*/
        } catch(IOException e){
            throw new FileNotValidException("FILE_ERROR_FORMAT", e);
            }
            userList.imprimirLista();

    }
    public static User buscarUsuario(String usuarioBuscado, ListaEnlazada<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            User usuarioActual = userList.get(i);
            if (usuarioActual != null && usuarioActual.equals(usuarioBuscado)) {
                return usuarioActual; // Devuelve la posición del usuario encontrado
            }
        }
        return null; // Si no se encuentra el usuario, se devuelve null
    }
    public static HashTag buscarHashtagPorTexto(String textoBuscado, ListaEnlazada<HashTag> hashtagList) {
        for (int i = 0; i < hashtagList.size(); i++) {
            HashTag hashtagActual = hashtagList.get(i);
            if (hashtagActual != null && hashtagActual.getTextHashTag().equals(textoBuscado)) {
                return hashtagActual;
            }
        }
        return null;
    }

    public static void main(String[] args) throws FileNotValidException, IOException {
        //ListaEnlazada<String> driversLinkedList = new ListaEnlazada<>();
        //getDriversFromFile(driversLinkedList);
        getCsvInfo();
        //System.out.println(userList);
        //menu();

    }
}