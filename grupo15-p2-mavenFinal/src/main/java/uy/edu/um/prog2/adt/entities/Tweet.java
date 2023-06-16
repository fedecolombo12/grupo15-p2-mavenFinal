package uy.edu.um.prog2.adt.entities;

import uy.edu.um.prog2.adt.tads.Lista.Lista;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

import java.util.Objects;

public class Tweet implements Comparable<Tweet> {
    private long idTweet;
    private String contentTweet;
    private String sourceTweet;
    private boolean isRetweet;
    private String date;
    private final ListaEnlazada<HashTag> hashTagTweet ;
    private User user;

    public Tweet(long idTweet, String contentTweet, String sourceTweet, boolean isRetweet, ListaEnlazada<HashTag> hashTagTweet, String date) {
        this.idTweet = idTweet;
        this.contentTweet = contentTweet;
        this.sourceTweet = sourceTweet;
        this.isRetweet = isRetweet;
        this.hashTagTweet = new ListaEnlazada<>();
        this.date = date;
    }

    public Tweet() {
        hashTagTweet = new ListaEnlazada<>();
    }

    public long getIdTweet() {
        return idTweet;
    }

    public void setIdTweet(long idTweet) {
        this.idTweet = idTweet;
    }

    public String getContentTweet() {
        return contentTweet;
    }

    public void setContentTweet(String contentTweet) {
        this.contentTweet = contentTweet;
    }

    public String getSourceTweet() {
        return sourceTweet;
    }

    public void setSourceTweet(String sourceTweet) {
        this.sourceTweet = sourceTweet;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public Lista<HashTag> getHashTagTweet() {
        return hashTagTweet;
    }

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int compareTo(Tweet o) {
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(date, tweet.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
