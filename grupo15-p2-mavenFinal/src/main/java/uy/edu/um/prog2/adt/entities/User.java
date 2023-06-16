package uy.edu.um.prog2.adt.entities;

import uy.edu.um.prog2.adt.tads.Lista.Lista;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

import java.util.Objects;

public class User implements Comparable<User> {
    private long idUser = 0;
    private String name;
    boolean isVerified;
    int userFavourites;
    private ListaEnlazada<Tweet> listaTweet;

    private int countTweets;

    public User(long idUser, String name, boolean isVerified, int userFavourites) {
        this.idUser = idUser;
        this.name = name;
        this.listaTweet = new ListaEnlazada<>();
        this.isVerified = isVerified;
        this.userFavourites = userFavourites;
    }

    public User() {
        this.idUser = ++idUser;;
        this.listaTweet = new ListaEnlazada<>();
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListaEnlazada<Tweet> getlistaTweet() {
        return listaTweet;
    }

    public void setlistaTweet(ListaEnlazada<Tweet> listaTweet) {
        this.listaTweet = listaTweet;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public int getUserFavourites() {return userFavourites;}

    public void setUserFavourites(int userFavourites) {this.userFavourites = userFavourites;}

    public void setCountTweets(int countTweets){
        this.countTweets = countTweets;
    }

    public int getCountTweets(){
        return countTweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name == user.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}
