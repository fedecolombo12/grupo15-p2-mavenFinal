package uy.edu.um.prog2.adt.entities;

import java.util.Objects;

/* Se crean la entidad Hashtag la cual tiene un ID único y el contenido del hashtag.
    Además, se crea el contructor, getters y setters. Se implementa el metodo equals donde el valor a comparar
    sera el ID del hashtag para mas adelante corroborar si se encuentra en "hashtagList".
 */
public class HashTag implements Comparable<HashTag> {
    private long idHashTag;
    private String textHashTag;

    public HashTag(long idHashTag, String textHashTag) {
        this.idHashTag = idHashTag;
        this.textHashTag = textHashTag;
    }

    public HashTag() {
        this.idHashTag = idHashTag + 1;
    }

    public long getIdHashTag() {
        return idHashTag;
    }

    public void setIdHashTag(long idHashTag) {
        this.idHashTag = idHashTag;
    }

    public String getTextHashTag() {
        return textHashTag;
    }

    public void setTextHashTag(String textHashTag) {
        this.textHashTag = textHashTag;
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
