package uy.edu.um.prog2.adt;
import uy.edu.um.prog2.adt.entities.*;
import uy.edu.um.prog2.adt.exceptions.FileNotValidException;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;
import uy.edu.um.prog2.adt.tads.Hash.*;
import uy.edu.um.prog2.adt.tads.Heap.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.time.LocalDate;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ReadCSV {
    public ListaEnlazada<User> userList = new ListaEnlazada<>();
    public ListaEnlazada<HashTag> hashtagList = new ListaEnlazada<>();
    public ListaEnlazada<Tweet> tweetList = new ListaEnlazada<>();
    public ListaEnlazada<User> getUserList() {
        return userList;
    }
    public ListaEnlazada<HashTag> getHashtagList() {
        return hashtagList;
    }
    public ListaEnlazada<Tweet> getTweetList() {
        return tweetList;
    }

    public void getCsvInfo() throws FileNotValidException {
        final String csvFile = "grupo15-p2-mavenFinal/src/main/resources/f1_dataset_test.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            csvParser.iterator().next();  // Salta la primera fila que contiene los nombres de las columnas
            for (CSVRecord csvRecord : csvParser) {
                String[] values = csvRecord.values();
                try {
                    Tweet tweet = new Tweet();
                    tweet.setIdTweet(Long.parseLong(values[0]));
                    tweet.setContentTweet(values[10].toLowerCase());
                    tweet.setSourceTweet(values[12]);
                    tweet.setRetweet(Boolean.parseBoolean(values[13]));
                    String dateFormat = values[9];
                    tweet.setDate(timeOk(dateFormat));
                    tweetList.add(tweet);
                    User user = new User();
                    user.setName(values[1]);
                    user.setVerified(Boolean.parseBoolean(values[8]));
                    user.setUserFavourites((int) Double.parseDouble(values[7]));
                    if (userList.contains(user)) {
                        User exist = userList.searchT(user).getValue();
                        exist.getlistaTweet().add(tweet);
                    } else {
                        user.getlistaTweet().add(tweet);
                        userList.add(user);
                    }
                    ListaEnlazada<HashTag> hashTagTweet = new ListaEnlazada<>();
                    String hashtagsColumn = values[11];
                    if (!hashtagsColumn.isEmpty()) {
                        String[] hashtags = hashtagsColumn.replace("[", "").replace("]", "").replace("'", "").split(", ");
                        for (String hashtagText : hashtags) {
                            HashTag hashtag = new HashTag();
                            String trimmedHashtagText = hashtagText.trim();
                            hashtag.setTextHashTag(trimmedHashtagText);
                            hashTagTweet.add(hashtag);
                        }
                    }
                    tweet.setHashTagTweet(hashTagTweet);
                } catch (Exception Ignored) {
                    }
            }
        } catch (IOException e) {
            throw new FileNotValidException("FILE_ERROR_FORMAT", e);
        }
    }

    public String timeOk(String date) {
        String[] dateTimeParts = date.split(" "); // Dividir la cadena en fecha y hora
        String datePart = dateTimeParts[0]; // Obtener la parte de la fecha

        String[] dateComponents = datePart.split("-"); // Dividir la fecha en componentes (año, mes, día)
        String year = dateComponents[0];
        String month = dateComponents[1];
        String day = dateComponents[2];

        // Reorganizar los componentes de fecha para obtener el formato deseado (YYYY-MM-DD)
        String formattedDate = year + "-" + month + "-" + day;

        return formattedDate;
    }
}










    /*public static User buscarUsuario(String usuarioBuscado, ListaEnlazada<User> userList) {
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
    }*/
