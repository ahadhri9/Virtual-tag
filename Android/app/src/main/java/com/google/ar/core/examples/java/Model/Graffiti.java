package com.google.ar.core.examples.java.Model;

public class Graffiti {
    // Chaque graf possèdera :
    // un créateur,
    // une image,
    // les coordonnées GPS (lattitude/ longitude) de l'endroit où il a été posé,
    // un nombre de LIKE
    private String createur, image;
    private Double latitude, longitude;
    private Integer like;
    private String id;

    public Graffiti () {
    }

    public Graffiti (String createur, String image, Double latitude, Double longitude, Integer like, String id) {
        this.createur = createur;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.like = like;
        this.id = id;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "createur:"+this.createur+" "+"image:"+this.image+" "+"latitude:"+this.latitude+" "+"longitude:"+this.longitude+" "+"like:"+this.like;
    }
}
