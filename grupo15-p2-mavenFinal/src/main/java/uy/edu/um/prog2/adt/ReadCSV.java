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

    public void setUserList(ListaEnlazada<User> userList) {
        this.userList = userList;
    }
    public ListaEnlazada<HashTag> getHashtagList() {
        return hashtagList;
    }
    public void setHashtagList(ListaEnlazada<HashTag> hashtagList) {
        this.hashtagList = hashtagList;
    }
    public ListaEnlazada<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(ListaEnlazada<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public void getCsvInfo() throws FileNotValidException, IOException {
        final String csvFile = "grupo15-p2-mavenFinal/src/main/resources/f1_dataset_test.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT)) {
            csvParser.iterator().next();  // Salta la primera fila que contiene los nombres de las columnas
            long userIdCounter = 0;
            long hashtagIdCounter = 0;
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
                    String[] hashtags = hashtagsColumn.replace("[", "").replace("]", "").replace("'", "").split(", ");
                    for (String hashtagText : hashtags) {
                        String trimmedHashtagText = hashtagText.trim();
                        // Verifica si el hashtag ya existe en la lista
                        HashTag existingHashtag = buscarHashtagPorTexto(trimmedHashtagText, hashtagList);
                        if (existingHashtag != null) {
                            tweet.getHashTagTweet().add(existingHashtag);
                        } else { // El hashtag no existe en la lista, crea uno nuevo y asigna un ID único
                            HashTag hashtag = new HashTag();
                            hashtag.setIdHashTag(hashtagIdCounter++);
                            hashtag.setTextHashTag(trimmedHashtagText);
                            hashtagList.add(hashtag);  // Agrega el nuevo hashtag a la lista y al tweet
                            tweet.getHashTagTweet().add(hashtag);
                        }
                    }
                }
                user.getlistaTweet().add(tweet);
                tweetList.add(tweet);
            }

        } catch (IOException e) {
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
}