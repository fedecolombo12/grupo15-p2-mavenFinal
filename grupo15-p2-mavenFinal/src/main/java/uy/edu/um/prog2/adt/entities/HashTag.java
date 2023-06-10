package uy.edu.um.prog2.adt.entities;
import uy.edu.um.prog2.adt.tads.Lista.*;
import uy.edu.um.prog2.adt.tads.Lista.ListaEnlazada;

import java.util.Objects;

public class HashTag implements Comparable<HashTag> {
    private long idHashTag;
    private String textHashTag;
    private Lista<Tweet> tweetWithHashTag;

    public HashTag(long idHashTag, String textHashTag) {
        this.idHashTag = idHashTag;
        this.textHashTag = textHashTag;
        this.tweetWithHashTag = new ListaEnlazada<>();
    }

    public HashTag() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return idHashTag == hashTag.idHashTag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHashTag);
    }

    @Override
    public int compareTo(HashTag o) {
        return 0;
    }
}
