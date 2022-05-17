package com.google.ar.core.examples.java.Model;

public class Graffiti {
    // Chaque graf possèdera :
    // un créateur,
    // un nom/ pseudo (afin de le reconnaître),
    // une image,
    // les coordonnées GPS (lattitude/ longitude) de l'endroit où il a été posé.
    private String createur, nom, image;
    private Double latitude, longitude;

    public Graffiti () {
    }

    public Graffiti (String createur, String nom, String image, Double latitude, Double longitude) {
        this.createur = createur;
        this.nom = nom;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
