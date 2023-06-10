package uy.edu.um.prog2.adt.entities;

import uy.edu.um.prog2.adt.tads.Lista.Lista;

import java.util.Objects;

public class Tweet implements Comparable<Tweet> {
    private long idTweet;
    private String contentTweet;
    private String sourceTweet;
    private boolean isRetweet;
    private User ownerTweet;
    private Lista<HashTag> hashTagTweet;


    public Tweet(long idTweet, String contentTweet, String sourceTweet, boolean isRetweet, User ownerTweet) {
        this.idTweet = idTweet;
        this.contentTweet = contentTweet;
        this.sourceTweet = sourceTweet;
        this.isRetweet = isRetweet;
        this.ownerTweet = ownerTweet;
    }

    public Tweet() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return idTweet == tweet.idTweet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTweet);
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

    public User getOwnerTweet() {
        return ownerTweet;
    }

    public void setOwnerTweet(User ownerTweet) {
        this.ownerTweet = ownerTweet;
    }

    @Override
    public int compareTo(Tweet o) {
        return 0;
    }

    public Lista<HashTag> getHashTagTweet() {
        return hashTagTweet;
    }

    public void setHashTagTweet(Lista<HashTag> hashTagTweet) {
        this.hashTagTweet = hashTagTweet;
    }
}
