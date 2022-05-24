package com.google.ar.core.examples.java.Model;

public class Winner {

    private String NFT, createur, graf;
    private Integer like, date;

    public Winner () {
    }

    public Winner (String NFT, String createur, Integer date, String graf, Integer like) {
        this.NFT = NFT;
        this.createur = createur;
        this.graf = graf;
        this.like = like;
        this.date = date;
    }

    public String getNFT() {
        return NFT;
    }

    public void setNFT(String NFT) {
        this.NFT = NFT;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getGraf() {
        return graf;
    }

    public void setGraf(String graf) {
        this.graf = graf;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}
