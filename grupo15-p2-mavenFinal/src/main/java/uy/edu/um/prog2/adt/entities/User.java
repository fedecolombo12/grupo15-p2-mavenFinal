package uy.edu.um.prog2.adt.entities;

import uy.edu.um.prog2.adt.tads.Lista.Lista;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

import java.util.Objects;

public class User {
    private long idUser;
    private String name;
    Lista<Tweet> listaTweet;

    public User(long idUser, String name) {
        this.idUser = idUser;
        this.name = name;
        this.listaTweet = new ListaEnlazada<>();
    }

    public User() {

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

    public Lista<Tweet> getlistaTweet() {
        return listaTweet;
    }

    public void setistaTweet(Lista<Tweet> listaTweet) {
        this.listaTweet = listaTweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }

}
