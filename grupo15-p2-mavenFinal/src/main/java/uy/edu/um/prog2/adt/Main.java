package uy.edu.um.prog2.adt;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.entities.HashTag;
import uy.edu.um.prog2.adt.entities.Tweet;
import uy.edu.um.prog2.adt.entities.User;
import uy.edu.um.prog2.adt.exceptions.FileNotValidException;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

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
    static ListaEnlazada<User> userList = new ListaEnlazada<>();

    public static void getCsvInfo() throws FileNotValidException, IOException {
        final String csvFile = "src/main/resources/f1_dataset_test.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            int count = 0;
            for (CSVRecord csvRecord : csvParser) {
                for (String value : csvRecord) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
                try {
                    Tweet tweet = new Tweet();
                    HashTag hashTag = new HashTag();
                    User userToAnalise = buscarUsuario(values[1]);
                    if (!userList.contains(userToAnalise)) { // Si el usuario no esta en la lista.
                        User user = new User();
                        user.setIdUser(Integer.parseInt(values[0]));
                        user.setName(values[1]);
                        user.setVerified(Boolean.parseBoolean(values[8]));
                        user.setUserFavourites(Integer.parseInt(values[7]));
                        //tweet.setIdTweet(nose);
                        tweet.setContentTweet(values[10]);
                        tweet.setSourceTweet(values[12]);
                        tweet.setRetweet(Boolean.parseBoolean(values[13]));
                        ///tweet.setDate(formattedDate);
                        hashTag.setTextHashTag(Arrays.toString(values[11].split(",")));
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
                    }

                    } catch (Exception ignored) {
                        }
                }
            } catch(IOException e){
                throw new FileNotValidException("FILE_ERROR_FORMAT", e);
                }
    }


    public static User buscarUsuario(String usuarioBuscado) {
        for (int i = 0; i < userList.size(); i++) {
            User usuarioActual = userList.get(i);
            if (usuarioActual.equals(usuarioBuscado)) {
                return usuarioActual; // Devuelve la posición del usuario encontrado
            }
        }
        return null; // Si no se encuentra el usuario, se devuelve null
    }

    public static void main(String[] args) throws FileNotValidException, IOException {
        //getDriversFromFile();
        getCsvInfo();
        //System.out.println(userList);
        //menu();
    }
}