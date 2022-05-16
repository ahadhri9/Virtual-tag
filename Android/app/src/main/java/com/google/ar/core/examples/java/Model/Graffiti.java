package com.google.ar.core.examples.java.Model;

public class Graffiti {
    // Chaque graf possèdera :
    // un créateur,
    // un nom/ pseudo (afin de le reconnaître),
    // une image,
    // les coordonnées GPS (lattitude/ longitude) de l'endroit où il a été posé.
    private String createur, nom, image, lattitude, longitude;

    public Graffiti () {
    }

    public Graffiti (String createur, String nom, String image, String lattitude, String longitude) {
        this.createur = createur;
        this.nom = nom;
        this.image = image;
        this.lattitude = lattitude;
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

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
